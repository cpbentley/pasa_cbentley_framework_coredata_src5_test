package pasa.cbentley.framework.core.data.src5.engine.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import pasa.cbentley.framework.core.data.src4.ex.StoreException;
import pasa.cbentley.framework.core.data.src4.ex.StoreNotOpenException;
import pasa.cbentley.framework.core.data.src5.ctx.CoreData5Ctx;
import pasa.cbentley.framework.core.data.src5.engine.RecordStoreHashMap;
import pasa.cbentley.framework.core.data.src5.interfaces.IRecordStoreManager;
import pasa.cbentley.framework.core.data.src5.rsm.RSMPureMemory;
import pasa.cbentley.framework.core.data.src5.tests.CoreData5Test;

public class TestRecordStoreHashMap extends CoreData5Test {

   public TestRecordStoreHashMap() {

   }

   private RecordStoreHashMap createNew() {
      CoreData5Ctx cdc = getCoreData5Ctx();
      IRecordStoreManager rsm = new RSMPureMemory(cdc);
      RecordStoreHashMap rsh = new RecordStoreHashMap(cdc,rsm, "teststore");
      return rsh;
   }

   private RecordStoreHashMap createNew(DataInputStream dis) throws IOException {
      CoreData5Ctx cdc5 = getCoreData5Ctx();
      IRecordStoreManager rsm = new RSMPureMemory(cdc5);
      RecordStoreHashMap rsh = new RecordStoreHashMap(cdc5,rsm, dis);
      return rsh;
   }

   public void testOpenClose() {
      RecordStoreHashMap rsh = createNew();
      try {
         rsh.closeRecordStore();
         assertEquals(false, true);
      } catch (StoreNotOpenException e) {
         assertEquals(true, true);
      }

      try {

         rsh.setOpen(true);

         rsh.addRecord(new byte[] { 1, 2 }, 0, 2);

      } catch (StoreException e) {
         e.printStackTrace();
         assertEquals(false, true);
      }
      try {
         rsh.closeRecordStore();
         assertEquals(true, true);
      } catch (StoreNotOpenException e) {
         assertEquals(false, true);
      }

      assertEquals(false, rsh.isOpen());
   }

   public void testReadWrite() {
      RecordStoreHashMap rsh = createNew();

      rsh.setOpen(true);

      try {
         rsh.addRecord(null, 0, 0);
         rsh.addRecord(new byte[] { 1 }, 0, 1);

         assertEquals(2, rsh.getVersion());
         assertEquals(2, rsh.getNumRecords());
         assertEquals(2, rsh.getNextRecordID());
         assertEquals(null, rsh.getRecord(0));
         assertEquals(1, rsh.getRecord(1).length);

         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(bos);
         rsh.write(dos);

         byte[] data = bos.toByteArray();
         ByteArrayInputStream bis = new ByteArrayInputStream(data);
         DataInputStream dis = new DataInputStream(bis);

         RecordStoreHashMap rsh2 = createNew(dis);

         rsh2.setOpen(true);
         assertEquals(2, rsh2.getVersion());
         assertEquals(2, rsh2.getNumRecords());
         assertEquals(2, rsh2.getNextRecordID());
         assertEquals(null, rsh2.getRecord(0));
         assertEquals(1, rsh2.getRecord(1).length);
      } catch (StoreException | IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         assertTrue(false);
      }
   }

   public void testGetBytes() {
      RecordStoreHashMap rsh = createNew();
      try {

         assertEquals(0, rsh.getBase());
         rsh.setOpen(true);

         assertEquals(0, rsh.getNextRecordID());

         byte[] b = rsh.getRecord(0);

         assertTrue(false);
      } catch (StoreException e) {
         assertTrue(true);
      }

      try {
         rsh.addRecord(null, 0, 0);
         byte[] b = rsh.getRecord(0);
         assertEquals(null, b);

         rsh.closeRecordStore();
         assertTrue(true);
      } catch (StoreNotOpenException e) {
         assertEquals(false, true);
      } catch (StoreException e) {
         assertTrue(false);
      }

      assertEquals(false, rsh.isOpen());
   }
}
