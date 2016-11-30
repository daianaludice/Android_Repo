<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.admin.myapplication.MainActivity">

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/edt1"
        android:layout_alignParentTop="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:text="num1" />

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/edt2"
        android:layout_alignBottom="@+id/edt1"
        android:layout_toRightOf="@+id/edt1"
        android:layout_toEndOf="@+id/edt1"
        android:layout_marginLeft="39dp"
        android:layout_marginStart="39dp"
        android:text="num2" />

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/edt3"
        android:layout_alignParentTop="true"
        android:layout_toRightOf="@+id/edt2"
        android:layout_toEndOf="@+id/edt2"
        android:layout_marginLeft="31dp"
        android:layout_marginStart="31dp"
        android:text="num3" />

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/edt4"
        android:layout_alignParentTop="true"
        android:layout_toRightOf="@+id/edt3"
        android:layout_toEndOf="@+id/edt3"
        android:layout_marginLeft="39dp"
        android:layout_marginStart="39dp"
        android:text="num4" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="avg"
        android:id="@+id/bt1"
        android:layout_below="@+id/edt1"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_marginTop="49dp" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="min"
        android:id="@+id/bt2"
        android:layout_alignBottom="@+id/bt1"
        android:layout_alignLeft="@+id/edt3"
        android:layout_alignStart="@+id/edt3" />

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/edtre"
        android:layout_centerVertical="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:text="result" />
</RelativeLayout>