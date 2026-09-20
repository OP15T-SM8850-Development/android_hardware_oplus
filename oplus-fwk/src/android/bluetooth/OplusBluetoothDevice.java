package android.bluetooth;

/**
 * Closure stub for OEM android.bluetooth.OplusBluetoothDevice.
 * Referenced by OplusCamera via new-instance + invoke-virtual getOplusBluetoothClass().
 * Real class (OOS oplus-framework.jar) is a plain final wrapper over BluetoothDevice
 * that queries BluetoothAdapterExtImpl.getRemoteClass(). We return a safe default.
 */
public class OplusBluetoothDevice {

    private android.bluetooth.BluetoothDevice mBluetoothDevice;

    public OplusBluetoothDevice(android.bluetooth.BluetoothDevice device) {
        mBluetoothDevice = device;
    }

    public int getOplusBluetoothClass() {
        return 0;
    }
}
