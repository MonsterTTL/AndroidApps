package com.example.generateprojection.helper;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import kotlin.jvm.internal.SerializedIr;

public class AudioHelperGson {

   @SerializedName("ws")
   public ArrayList<wsBean> ws;
   public class wsBean{
       @SerializedName("cw")
       public ArrayList<cwBean> cw;
   }
   public class cwBean{
       @SerializedName("w")
       public String w;
   }
}
