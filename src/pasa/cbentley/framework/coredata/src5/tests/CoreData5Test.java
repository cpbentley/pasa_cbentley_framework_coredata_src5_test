package pasa.cbentley.framework.coredata.src5.tests;

import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.framework.coredata.src5.ctx.ConfigCoreData5Default;
import pasa.cbentley.framework.coredata.src5.ctx.CoreData5Ctx;
import pasa.cbentley.testing.engine.TestCaseBentley;

public abstract class CoreData5Test extends TestCaseBentley {

   protected CoreData5Ctx cdc5;

   private BOCtx          boc;

   public CoreData5Test() {
      boc = new BOCtx(uc);
      ConfigCoreData5Default configData = new ConfigCoreData5Default(uc);
      cdc5 = new CoreData5Ctx(boc, configData);
   }

   public void setupAbstract() {

   }

   public CoreData5Ctx getCoreData5Ctx() {
      return cdc5;
   }
}
