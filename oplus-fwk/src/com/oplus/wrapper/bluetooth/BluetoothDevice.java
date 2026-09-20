package com.oplus.wrapper.bluetooth;

public class BluetoothDevice {
    private final android.bluetooth.BluetoothDevice mBluetoothDevice;

    public BluetoothDevice(android.bluetooth.BluetoothDevice bluetoothDevice) {
        this.mBluetoothDevice = bluetoothDevice;
    }

    public boolean isConnected() {
        return false;
    }
}
