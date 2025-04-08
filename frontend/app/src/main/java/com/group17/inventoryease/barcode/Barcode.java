package com.group17.inventoryease.barcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.print.PrintHelper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.group17.inventoryease.R;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Barcode {

    private String text;
    private BarcodeFormat format;
    private int height;
    private int width;

    private Bitmap bitmap;

    public void createBarcode() throws WriterException {
        BarcodeEncoder encoder  = new BarcodeEncoder();
        Bitmap bitmap = encoder.encodeBitmap(text, format,width,height);
        this.bitmap = bitmap;
    }

    public void printBarcode(Context context){
        PrintHelper qrcodePrinter = new PrintHelper(context);
        qrcodePrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        qrcodePrinter.printBitmap("BarcodePrint", bitmap);
    }

    public Barcode(String text, BarcodeFormat format, int height, int width) throws WriterException {
        this.text = text;
        this.format = format;
        this.height = height;
        this.width = width;
        createBarcode();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BarcodeFormat getFormat() {
        return format;
    }

    public void setFormat(BarcodeFormat format) {
        this.format = format;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
