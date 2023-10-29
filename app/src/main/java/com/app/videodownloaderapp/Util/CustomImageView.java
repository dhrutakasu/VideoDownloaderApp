package com.app.videodownloaderapp.Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.yalantis.ucrop.view.CropImageView;

import java.util.Objects;

@SuppressLint({"AppCompatCustomView"})
public class CustomImageView extends ImageView {
    public Paint background;
    public int containerHeight;
    public int containerWidth;
    public float curX;
    public float curY;
    public float currentScale;
    public float d = CropImageView.DEFAULT_ASPECT_RATIO;
    public int defaultScale;
    public GestureDetector gestureDetector;
    public Bitmap imgBitmap = null;
    public boolean isAnimating = false;
    public float[] lastEvent = null;
    public Handler mHandler = new Handler();
    public Runnable mUpdateImagePositionTask = new Runnable() {
        public final void run() {
            CustomImageView customImageView = CustomImageView.this;
            if (Math.abs(customImageView.targetX - customImageView.curX) < 5.0f) {
                CustomImageView customImageView2 = CustomImageView.this;
                if (Math.abs(customImageView2.targetY - customImageView2.curY) < 5.0f) {
                    Log.e("zoom", "update pos if");
                    CustomImageView customImageView3 = CustomImageView.this;
                    customImageView3.isAnimating = false;
                    customImageView3.mHandler.removeCallbacks(customImageView3.mUpdateImagePositionTask);
                    float[] fArr = new float[9];
                    CustomImageView.this.matrix.getValues(fArr);
                    CustomImageView customImageView4 = CustomImageView.this;
                    customImageView4.currentScale = fArr[0];
                    float f = fArr[2];
                    customImageView4.curX = f;
                    float f2 = fArr[5];
                    customImageView4.curY = f2;
                    customImageView4.matrix.postTranslate(customImageView4.targetX - f, customImageView4.targetY - f2);
                    CustomImageView.this.invalidate();
                }
            }
            Log.e("zoom", "update pos else");
            CustomImageView customImageView5 = CustomImageView.this;
            customImageView5.isAnimating = true;
            float[] fArr2 = new float[9];
            customImageView5.matrix.getValues(fArr2);
            CustomImageView customImageView6 = CustomImageView.this;
            customImageView6.currentScale = fArr2[0];
            float f3 = fArr2[2];
            customImageView6.curX = f3;
            float f4 = fArr2[5];
            customImageView6.curY = f4;
            customImageView6.matrix.postTranslate((customImageView6.targetX - f3) * 0.3f, (customImageView6.targetY - f4) * 0.3f);
            CustomImageView.this.mHandler.postDelayed(this, 25);
            CustomImageView.this.invalidate();
        }
    };
    public Runnable mUpdateImageScale = new Runnable() {
        public final void run() {
            CustomImageView customImageView = CustomImageView.this;
            float f = customImageView.targetScale / customImageView.currentScale;
            float f2 = f - 1.0f;
            if (((double) Math.abs(f2)) > 0.05d) {
                CustomImageView customImageView2 = CustomImageView.this;
                customImageView2.isAnimating = true;
                float f3 = customImageView2.targetScale;
                float f4 = customImageView2.currentScale;
                if (f3 > f4) {
                    float f5 = (f2 * 0.2f) + 1.0f;
                    customImageView2.scaleChange = f5;
                    float f6 = f4 * f5;
                    customImageView2.currentScale = f6;
                    if (f6 > f3) {
                        customImageView2.currentScale = f6 / f5;
                        customImageView2.scaleChange = 1.0f;
                    }
                } else {
                    float f7 = 1.0f - ((1.0f - f) * 0.5f);
                    customImageView2.scaleChange = f7;
                    float f8 = f4 * f7;
                    customImageView2.currentScale = f8;
                    if (f8 < f3) {
                        customImageView2.currentScale = f8 / f7;
                        customImageView2.scaleChange = 1.0f;
                    }
                }
                float f9 = customImageView2.scaleChange;
                if (f9 != 1.0f) {
                    customImageView2.matrix.postScale(f9, f9, customImageView2.targetScaleX, customImageView2.targetScaleY);
                    CustomImageView customImageView3 = CustomImageView.this;
                    customImageView3.mHandler.postDelayed(customImageView3.mUpdateImageScale, 15);
                    CustomImageView.this.invalidate();
                    return;
                }
                customImageView2.isAnimating = false;
                customImageView2.scaleChange = 1.0f;
                Matrix matrix = customImageView2.matrix;
                float f10 = customImageView2.currentScale;
                matrix.postScale(f3 / f10, f3 / f10, customImageView2.targetScaleX, customImageView2.targetScaleY);
                CustomImageView customImageView4 = CustomImageView.this;
                customImageView4.currentScale = customImageView4.targetScale;
                customImageView4.mHandler.removeCallbacks(customImageView4.mUpdateImageScale);
                CustomImageView.this.invalidate();
                CustomImageView.this.checkImageConstraints();
                return;
            }
            CustomImageView customImageView5 = CustomImageView.this;
            customImageView5.isAnimating = false;
            customImageView5.scaleChange = 1.0f;
            Matrix matrix2 = customImageView5.matrix;
            float f11 = customImageView5.targetScale;
            float f12 = customImageView5.currentScale;
            matrix2.postScale(f11 / f12, f11 / f12, customImageView5.targetScaleX, customImageView5.targetScaleY);
            CustomImageView customImageView6 = CustomImageView.this;
            customImageView6.currentScale = customImageView6.targetScale;
            customImageView6.mHandler.removeCallbacks(customImageView6.mUpdateImageScale);
            CustomImageView.this.invalidate();
            CustomImageView.this.checkImageConstraints();
        }
    };
    public Matrix matrix = new Matrix();
    public float maxScale = 8.0f;
    public PointF mid = new PointF();
    public float minScale;
    public int mode = 0;
    public float oldDist = 1.0f;
    public Matrix savedMatrix = new Matrix();
    public float scaleChange;
    public PointF start = new PointF();
    public float targetScale;
    public float targetScaleX;
    public float targetScaleY;
    public float targetX;
    public float targetY;

    public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        public MyGestureDetector() {
        }

        public final boolean onDoubleTap(MotionEvent motionEvent) {
            CustomImageView customImageView = CustomImageView.this;
            if (customImageView.isAnimating) {
                return true;
            }
            customImageView.scaleChange = 1.0f;
            customImageView.isAnimating = true;
            customImageView.targetScaleX = motionEvent.getX();
            CustomImageView.this.targetScaleY = motionEvent.getY();
            CustomImageView customImageView2 = CustomImageView.this;
            if (((double) Math.abs(customImageView2.currentScale - customImageView2.maxScale)) > 0.1d) {
                CustomImageView customImageView3 = CustomImageView.this;
                customImageView3.targetScale = customImageView3.maxScale;
            } else {
                CustomImageView customImageView4 = CustomImageView.this;
                customImageView4.targetScale = customImageView4.minScale;
            }
            CustomImageView customImageView5 = CustomImageView.this;
            float f = customImageView5.targetScale;
            float f2 = customImageView5.currentScale;
            Objects.requireNonNull(customImageView5);
            CustomImageView customImageView6 = CustomImageView.this;
            customImageView6.mHandler.removeCallbacks(customImageView6.mUpdateImageScale);
            CustomImageView customImageView7 = CustomImageView.this;
            customImageView7.mHandler.post(customImageView7.mUpdateImageScale);
            return true;
        }

        public final boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }
    }

    public CustomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        float f = context.getResources().getDisplayMetrics().density;
        this.background = new Paint();
        this.gestureDetector = new GestureDetector(new MyGestureDetector());
        this.defaultScale = 0;
    }

    public final void checkImageConstraints() {
        Log.e("zoom", "checkImageConstraints");
        if (this.imgBitmap != null) {
            float[] fArr = new float[9];
            this.matrix.getValues(fArr);
            this.currentScale = fArr[0];
            this.matrix.getValues(fArr);
            this.currentScale = fArr[0];
            this.curX = fArr[2];
            this.curY = fArr[5];
            int width = this.containerWidth - ((int) (((float) this.imgBitmap.getWidth()) * this.currentScale));
            int height = this.containerHeight - ((int) (((float) this.imgBitmap.getHeight()) * this.currentScale));
            if (width < 0) {
                float f = this.curX;
                if (f > CropImageView.DEFAULT_ASPECT_RATIO) {
                    this.targetX = CropImageView.DEFAULT_ASPECT_RATIO;
                } else {
                    float f2 = (float) width;
                    if (f < f2) {
                        this.targetX = f2;
                    }
                }
            } else {
                this.targetX = (float) (width / 2);
            }
            if (height < 0) {
                float f3 = this.curY;
                if (f3 > CropImageView.DEFAULT_ASPECT_RATIO) {
                    this.targetY = CropImageView.DEFAULT_ASPECT_RATIO;
                    return;
                }
                float f4 = (float) height;
                if (f3 < f4) {
                    this.targetY = f4;
                    return;
                }
                return;
            }
            this.targetY = (float) (height / 2);
        }
    }

    public int getDefaultScale() {
        return this.defaultScale;
    }

    public Bitmap getImageBitmap() {
        return this.imgBitmap;
    }

    public final void onDraw(Canvas canvas) {
        Bitmap bitmap = this.imgBitmap;
        if (bitmap != null && canvas != null) {
            canvas.drawBitmap(bitmap, this.matrix, this.background);
        }
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5;
        float f;
        int i6;
        super.onSizeChanged(i, i2, i3, i4);
        this.containerWidth = i;
        this.containerHeight = i2;
        Bitmap bitmap = this.imgBitmap;
        if (bitmap != null) {
            int height = bitmap.getHeight();
            int width = this.imgBitmap.getWidth();
            int i7 = 0;
            if (this.defaultScale == 0) {
                int i8 = this.containerWidth;
                if (width > i8) {
                    f = ((float) i8) / ((float) width);
                    i6 = (this.containerHeight - ((int) (((float) height) * f))) / 2;
                    this.matrix.setScale(f, f);
                    this.matrix.postTranslate(CropImageView.DEFAULT_ASPECT_RATIO, (float) i6);
                } else {
                    float f2 = ((float) this.containerHeight) / ((float) height);
                    int i9 = (i8 - ((int) (((float) width) * f2))) / 2;
                    this.matrix.setScale(f2, f2);
                    this.matrix.postTranslate((float) i9, CropImageView.DEFAULT_ASPECT_RATIO);
                    i6 = 0;
                    i7 = i9;
                    f = f2;
                }
                this.curX = (float) i7;
                this.curY = (float) i6;
                this.currentScale = f;
                this.minScale = f;
            } else {
                int i10 = this.containerWidth;
                if (width > i10) {
                    i5 = (this.containerHeight - height) / 2;
                    this.matrix.postTranslate(CropImageView.DEFAULT_ASPECT_RATIO, (float) i5);
                } else {
                    int i11 = (i10 - width) / 2;
                    this.matrix.postTranslate((float) i11, CropImageView.DEFAULT_ASPECT_RATIO);
                    i5 = 0;
                    i7 = i11;
                }
                this.curX = (float) i7;
                this.curY = (float) i5;
                this.currentScale = 1.0f;
                this.minScale = 1.0f;
            }
            invalidate();
        }
    }

    @SuppressLint({"LongLogTag"})
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.gestureDetector.onTouchEvent(motionEvent) || this.isAnimating) {
            return true;
        }
        motionEvent.getX();
        motionEvent.getY();
        float[] fArr = new float[9];
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            Log.e("Image", "ACTION_DOWN");
            if (!this.isAnimating) {
                this.savedMatrix.set(this.matrix);
                motionEvent.getX();
                this.start.set(motionEvent.getX(), motionEvent.getY());
                this.mode = 1;
            }
            this.lastEvent = null;
        } else if (action == 1) {
            Log.e("Image", "ACTION_UP");
            if (Math.abs(CropImageView.DEFAULT_ASPECT_RATIO - motionEvent.getX()) < 5.0f) {
                return true;
            }
        } else if (action == 2) {
            Log.e("Image", "ACTION_MOVE");
            int i = this.mode;
            if (i == 1 && !this.isAnimating) {
                this.matrix.set(this.savedMatrix);
                this.matrix.postTranslate(motionEvent.getX() - this.start.x, motionEvent.getY() - this.start.y);
                this.matrix.getValues(fArr);
                this.curX = fArr[2];
                this.curY = fArr[5];
                this.currentScale = fArr[0];
            } else if (i == 2 && !this.isAnimating) {
                float spacing = spacing(motionEvent);
                if (spacing > 10.0f) {
                    this.matrix.set(this.savedMatrix);
                    float f = spacing / this.oldDist;
                    this.matrix.getValues(fArr);
                    float f2 = fArr[0];
                    this.currentScale = f2;
                    float f3 = f2 * f;
                    if (f3 <= this.minScale) {
                        Log.e("zoom", "if");
                        Matrix matrix2 = this.matrix;
                        PointF pointF = this.mid;
                        matrix2.postScale(f, f, pointF.x, pointF.y);
                    } else if (f3 >= this.maxScale) {
                        Log.e("zoom", "else if");
                        Matrix matrix3 = this.matrix;
                        PointF pointF2 = this.mid;
                        matrix3.postScale(f, f, pointF2.x, pointF2.y);
                    } else {
                        Log.e("zoom", "else");
                        Matrix matrix4 = this.matrix;
                        PointF pointF3 = this.mid;
                        matrix4.postScale(f, f, pointF3.x, pointF3.y);
                    }
                    this.matrix.getValues(fArr);
                    this.curX = fArr[2];
                    this.curY = fArr[5];
                    this.currentScale = fArr[0];
                }
                if ((this.lastEvent != null && motionEvent.getPointerCount() == 2) || motionEvent.getPointerCount() == 3) {
                    Log.e("ACTION_MOVE", "doRotationEvent");
                    float rotation = rotation(motionEvent) - this.d;
                    this.matrix.getValues(fArr);
                    float f4 = fArr[2];
                    this.curX = f4;
                    float f5 = fArr[5];
                    this.curY = f5;
                    float f6 = fArr[0];
                    this.currentScale = f6;
                    this.matrix.postRotate(rotation, f4 + (((float) (this.containerWidth / 2)) * f6), f5 + (((float) (this.containerHeight / 2)) * f6));
                }
            }
        } else if (action == 5) {
            Log.e("Image", "ACTION_POINTER_DOWN");
            float spacing2 = spacing(motionEvent);
            this.oldDist = spacing2;
            if (spacing2 > 10.0f) {
                this.savedMatrix.set(this.matrix);
                this.mid.set((motionEvent.getX(1) + motionEvent.getX(0)) / 2.0f, (motionEvent.getY(1) + motionEvent.getY(0)) / 2.0f);
                this.mode = 2;
            }
            float[] fArr2 = new float[4];
            this.lastEvent = fArr2;
            fArr2[0] = motionEvent.getX(0);
            this.lastEvent[1] = motionEvent.getX(1);
            this.lastEvent[2] = motionEvent.getY(0);
            this.lastEvent[3] = motionEvent.getY(1);
            this.d = rotation(motionEvent);
        } else if (action == 6) {
            Log.e("Image", "ACTION_POINTER_UP");
            this.mode = 0;
            this.matrix.getValues(fArr);
            this.curX = fArr[2];
            this.curY = fArr[5];
            this.currentScale = fArr[0];
            this.lastEvent = null;
            if (!this.isAnimating) {
                checkImageConstraints();
            }
        }
        invalidate();
        return true;
    }

    public final float rotation(MotionEvent motionEvent) {
        return (float) Math.toDegrees(Math.atan2((double) (motionEvent.getY(0) - motionEvent.getY(1)), (double) (motionEvent.getX(0) - motionEvent.getX(1))));
    }

    public void setDefaultScale(int i) {
        this.defaultScale = i;
    }

    public void setImageBitmap(Bitmap bitmap) {
        int i;
        float f;
        int i2;
        if (bitmap != null) {
            this.imgBitmap = bitmap;
            this.containerWidth = getWidth();
            this.containerHeight = getHeight();
            int height = this.imgBitmap.getHeight();
            int width = this.imgBitmap.getWidth();
            this.matrix.reset();
            int i3 = 0;
            if (this.defaultScale == 0) {
                int i4 = this.containerWidth;
                if (width > i4) {
                    f = ((float) i4) / ((float) width);
                    i2 = (this.containerHeight - ((int) (((float) height) * f))) / 2;
                    this.matrix.setScale(f, f);
                    this.matrix.postTranslate(CropImageView.DEFAULT_ASPECT_RATIO, (float) i2);
                } else {
                    float f2 = ((float) this.containerHeight) / ((float) height);
                    int i5 = (i4 - ((int) (((float) width) * f2))) / 2;
                    this.matrix.setScale(f2, f2);
                    this.matrix.postTranslate((float) i5, CropImageView.DEFAULT_ASPECT_RATIO);
                    i2 = 0;
                    i3 = i5;
                    f = f2;
                }
                this.curX = (float) i3;
                this.curY = (float) i2;
                this.currentScale = f;
                this.minScale = f;
            } else {
                int i6 = this.containerWidth;
                if (width > i6) {
                    int i7 = this.containerHeight;
                    if (height > i7) {
                        i = 0;
                    } else {
                        i = (i7 - height) / 2;
                    }
                    this.matrix.postTranslate(CropImageView.DEFAULT_ASPECT_RATIO, (float) i);
                } else {
                    int i8 = (i6 - width) / 2;
                    int i9 = this.containerHeight;
                    if (height <= i9) {
                        i3 = (i9 - height) / 2;
                    }
                    this.matrix.postTranslate((float) i8, CropImageView.DEFAULT_ASPECT_RATIO);
                    i = i3;
                    i3 = i8;
                }
                this.curX = (float) i3;
                this.curY = (float) i;
                this.currentScale = 1.0f;
                this.minScale = 1.0f;
            }
            invalidate();
            return;
        }
        Log.d("ZoomableImageView", "bitmap is null");
    }

    public final float spacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((y * y) + (x * x)));
    }
}
