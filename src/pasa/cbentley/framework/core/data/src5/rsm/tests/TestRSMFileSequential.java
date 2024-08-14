package pasa.cbentley.framework.core.data.src5.rsm.tests;

import java.io.File;

import pasa.cbentley.framework.core.data.src5.rsm.RSMFileSequential;
import pasa.cbentley.framework.core.data.src5.tests.CoreData5Test;

public class TestRSMFileSequential extends CoreData5Test {

   public TestRSMFileSequential() {

   }

   public void testNew() {
      String fs = System.getProperty("file.separator");
      String dir = System.getProperty("user.dir") + fs + "data";
      File fileDir = new File(dir);

      RSMFileSequential rms = new RSMFileSequential(cdc5, fileDir);

   }
}
