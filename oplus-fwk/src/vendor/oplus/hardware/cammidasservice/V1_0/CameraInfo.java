package vendor.oplus.hardware.cammidasservice.V1_0;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;
public final class CameraInfo {
 public int physicalCameraId=0;
 public int isActive=0;
 public SensorDimension sensorOutDimension=new SensorDimension();
 public final boolean equals(Object o){if(this==o)return true;if(o==null||o.getClass()!=CameraInfo.class)return false;CameraInfo x=(CameraInfo)o;return physicalCameraId==x.physicalCameraId&&isActive==x.isActive&&HidlSupport.deepEquals(sensorOutDimension,x.sensorOutDimension);}
 public final int hashCode(){return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(physicalCameraId))),Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(isActive))),Integer.valueOf(HidlSupport.deepHashCode(sensorOutDimension)));}
 public final String toString(){return "{.physicalCameraId = "+physicalCameraId+", .isActive = "+isActive+", .sensorOutDimension = "+sensorOutDimension+"}";}
 public final void readFromParcel(HwParcel p){HwBlob b=p.readBuffer(24L);readEmbeddedFromParcel(p,b,0L);}
 public static final ArrayList<CameraInfo> readVectorFromParcel(HwParcel p){ArrayList<CameraInfo>r=new ArrayList<>();HwBlob b=p.readBuffer(16L);int n=b.getInt32(8L);HwBlob c=p.readEmbeddedBuffer(n*24L,b.handle(),0L,true);for(int i=0;i<n;i++){CameraInfo x=new CameraInfo();x.readEmbeddedFromParcel(p,c,i*24L);r.add(x);}return r;}
 public final void readEmbeddedFromParcel(HwParcel p,HwBlob b,long o){physicalCameraId=b.getInt32(o);isActive=b.getInt32(o+4);sensorOutDimension.readEmbeddedFromParcel(p,b,o+8);}
 public final void writeToParcel(HwParcel p){HwBlob b=new HwBlob(24);writeEmbeddedToBlob(b,0L);p.writeBuffer(b);}
 public static final void writeVectorToParcel(HwParcel p,ArrayList<CameraInfo>v){HwBlob b=new HwBlob(16);int n=v.size();b.putInt32(8L,n);b.putBool(12L,false);HwBlob c=new HwBlob(n*24);for(int i=0;i<n;i++)v.get(i).writeEmbeddedToBlob(c,i*24L);b.putBlob(0L,c);p.writeBuffer(b);}
 public final void writeEmbeddedToBlob(HwBlob b,long o){b.putInt32(o,physicalCameraId);b.putInt32(o+4,isActive);sensorOutDimension.writeEmbeddedToBlob(b,o+8);}
}

