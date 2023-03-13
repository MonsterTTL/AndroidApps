package com.example.camera;

import static com.example.camera.R.*;
import static com.example.camera.R.id.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

@Route(path = "/camera/cameraActivity")
public class MainActivity extends AppCompatActivity {

    TextureView previewTexture;//用于显示预览的
    //Button flash;
    int zoomLevel = 100;//初始焦距
    //Button takePic;//拍照按钮
    //Button reverseBtn;//转换前置和后置摄像头
    private String cameraId;//相机的唯一代号
    private String[] cameraIds;//所有相机的Id
    //相机有关的类
    protected CameraDevice cameraDevice;//代表一个相机设备
    protected CameraCaptureSession cameraCaptureSession;//代表一个捕获会话
    protected CaptureRequest captureRequest;//代表一个捕获请求，需要用Builder构建
    protected CaptureRequest.Builder captureRequestBuilder;
    //用于构建捕获请求
    protected SeekBar seekBar;

    private Size imageDimension;  //输出图片的尺寸
    //在 Java 中，Size 类代表了一个尺寸大小，通常用来表示一个矩形的宽和高。在 Android 中，Size 类也被广泛使用，用于表示相机预览或图片的尺寸大小。
    private ImageReader imageReader; //用于读取捕获的照片
    private File file;//用于保存文件
    private static final int REQUEST_CAMERA_PERMISSION = 200;//请求码
    private boolean mFlashSupported = false;
    private Handler mBackgroundHandler;
    //背景Handler
    private HandlerThread mBackgroundThread;

    private CircleImageView flash;
    private CircleImageView reverseBtn;

    //一个整型的键值对，用来存储相机的翻转问题
    //创建了一个名为ORIENTATIONS的SparseIntArray对象。
    //使用append()方法将四个键值对添加到ORIENTATIONS对象中。
    //每个键值对表示设备的旋转角度和JPEG图像的方向信息之间的映射关系。
    //当设备的旋转角度为0度时，JPEG图像的方向为90度。
    //当设备的旋转角度为90度时，JPEG图像的方向为0度。
    //当设备的旋转角度为180度时，JPEG图像的方向为270度。
    //当设备的旋转角度为270度时，JPEG图像的方向为180度。
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    //静态代码块，程序运行前将其初始化
        static {
            ORIENTATIONS.append(Surface.ROTATION_0,90);
            ORIENTATIONS.append(Surface.ROTATION_90,0);
            ORIENTATIONS.append(Surface.ROTATION_180,270);
            ORIENTATIONS.append(Surface.ROTATION_270,180);
        }

    private String faceFrontCameraId;
    private String faceBackCameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity_main);
        previewTexture = findViewById(R.id.previewTexture);
        seekBar = findViewById(R.id.seekBar);
        previewTexture.setSurfaceTextureListener(textureListener);
        flash = findViewById(flashBtn_Cir);
        reverseBtn = findViewById(reverseBtn_Cir);
        reverseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseCamera();
            }
        });
        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mFlashSupported){
                    captureRequestBuilder.set(CaptureRequest.FLASH_MODE,CaptureRequest.FLASH_MODE_TORCH);
                }else {
                    captureRequestBuilder.set(CaptureRequest.FLASH_MODE,CaptureRequest.FLASH_MODE_OFF);
                }
                mFlashSupported = !mFlashSupported;
                try{
                    cameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(),null,mBackgroundHandler);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                zoomLevel = progress;
                captureRequestBuilder.set(CaptureRequest.CONTROL_ZOOM_RATIO,(float)(zoomLevel/100.0f));
                try {
                    cameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(),null,mBackgroundHandler);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            private Rect getZoomRect(float zoomRatio){
                int centerX = imageDimension.getWidth()/2;
                int centerY = imageDimension.getHeight()/2;
                int zoomWidth = (int) (imageDimension.getWidth()/zoomRatio);
                int zoomHeight = (int) (imageDimension.getHeight()/zoomRatio);
                int zoomLeft = centerX - zoomWidth / 2;
                int zoomTop = centerY - zoomHeight / 2;
                return new Rect(zoomLeft,zoomTop,zoomLeft+zoomWidth,zoomTop+zoomHeight);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });




        }

        //一个用于监听TextureView的监听器，它监听TextureView状态的变化并且执行相对应的回调函数
        TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
            openCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

        }
        };

        //相机设备的状态的监听器
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            cameraDevice = camera;
            createCameraPreview();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            cameraDevice.close();
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            cameraDevice.close();
            cameraDevice = null ;
        }
     };

    //捕获会话的回调，监听的是捕获的状态，开始捕获，正在捕获，捕获完成等...
    final CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            Toast.makeText(MainActivity.this, "Saved:"+file, Toast.LENGTH_SHORT).show();

        }
    }    ;

    //启动相机线程
    protected void startBackgroundThread(){
        mBackgroundThread = new HandlerThread("Camera Thread");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    //停止相机线程
    protected void stopBackgroundThread(){
        mBackgroundThread.quitSafely();
        //停止后台线程的Looper循环
        try{
            mBackgroundThread.join();
            //等待后台线程完成执行
            mBackgroundThread = null;
            mBackgroundHandler = null;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void takePicture(){
        if(null == cameraDevice){
            return;
        }
        //获取相机管理器
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
            Size[] jpegSizes = null;
            if(characteristics != null){
                jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                        .getOutputSizes(ImageFormat.JPEG);
            }
            int width = 640;int height = 480;
            if(jpegSizes != null && 0 < jpegSizes.length){
                width = jpegSizes[0].getWidth();
                height = jpegSizes[0].getHeight();
            }
            ImageReader reader = ImageReader.newInstance(width,height,ImageFormat.JPEG,1);
            List<Surface> outputSurfaces = new ArrayList<Surface>(2);
            outputSurfaces.add(reader.getSurface());
            outputSurfaces.add(new Surface(previewTexture.getSurfaceTexture()));
            //创建一个捕获请求
            final CaptureRequest.Builder captureBuilder =
                    cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(reader.getSurface());
            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
            //方向
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION,ORIENTATIONS.get(rotation));
            final File file1 = new File(Environment.getExternalStorageDirectory()+"/pic.jpg");
            ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
                @Override
                public void onImageAvailable(ImageReader reader) {
                    Image image = null ;
                    try{
                        image = reader.acquireLatestImage();
                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                        byte[] bytes = new byte[buffer.capacity()];
                        buffer.get(bytes);
                        save(bytes);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        if(image != null)
                            image.close();
                    }
                }

                private void save(byte[] bytes)throws IOException{
                    OutputStream output = null;
                    try{
                        output = new FileOutputStream(file);
                        output.write(bytes);
                    }finally{
                        if(null != output){
                            output.close();
                        }
                    }
                }
            };

            reader.setOnImageAvailableListener(readerListener,mBackgroundHandler);
            final CameraCaptureSession.CaptureCallback captureListener =
                    new CameraCaptureSession.CaptureCallback() {
                        @Override
                        public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                            super.onCaptureCompleted(session, request, result);
                            Toast.makeText(MainActivity.this, "Saved:"+file1, Toast.LENGTH_SHORT).show();

                        }
                    };
            cameraDevice.createCaptureSession(outputSurfaces, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    try{
                        session.capture(captureBuilder.build(),captureListener,mBackgroundHandler);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                }
            },mBackgroundHandler);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    protected void createCameraPreview(){
        try{

            SurfaceTexture texture = previewTexture.getSurfaceTexture();
            texture.setDefaultBufferSize(imageDimension.getWidth(),imageDimension.getHeight());
            Surface surface = new Surface(texture);
            //创建一个预览请求

           // captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,CaptureRequest.CONTROL_AF_MODE_AUTO);
           // captureRequestBuilder.set(CaptureRequest.CONTROL_ZOOM_RATIO,(float)zoomLevel/100.0f );
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);

            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    if(null == cameraDevice){
                        return ;
                    }
                    cameraCaptureSession = session;
                    updatePreview();
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                }
            },null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openCamera(){
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            //获取第一个摄像头的Id,一般为后置摄像头

            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];

            //请求运行时权限
            if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CAMERA_PERMISSION
                        );
                return ;
            }
            manager.openCamera(cameraId,stateCallback,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void updatePreview(){
        if(null == cameraDevice){
            return;
        }

        captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE,CameraMetadata.CONTROL_MODE_AUTO);
        captureRequestBuilder.set(CaptureRequest.CONTROL_ZOOM_RATIO,zoomLevel/100.0f);
        try{
            cameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(),null,mBackgroundHandler);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void closeCamera(){
        if(null != cameraDevice){
            cameraDevice.close();
            cameraDevice = null;
        }
        if(null != imageReader){
            imageReader.close();
            imageReader = null;
        }
    }

    protected void reverseCamera(){
        if(cameraId == faceFrontCameraId){
            cameraId = faceBackCameraId;
        }else{
            cameraId = faceFrontCameraId;
        }
        closeCamera();
        openCamera();
    }

    //用于初始化相机的参数
    protected void InitCameraInformation(){
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraIds = manager.getCameraIdList();
            for(String nID:cameraIds){
                //获取各个相机的具体参数
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(nID);
                if(characteristics.get(CameraCharacteristics.LENS_FACING)
                        == CameraCharacteristics.LENS_FACING_FRONT){
                    faceFrontCameraId = nID;
                }else{
                    faceBackCameraId = nID;
                }
            }
            cameraId = faceBackCameraId;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CAMERA_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startBackgroundThread();
        InitCameraInformation();
        //如果TextureView的SurfaceTexture可用于渲染
        if(previewTexture.isAvailable()){
            openCamera();
        }else {
            previewTexture.setSurfaceTextureListener(textureListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        stopBackgroundThread();
        super.onPause();
    }
}