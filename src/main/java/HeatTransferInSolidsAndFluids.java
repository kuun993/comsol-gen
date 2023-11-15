/*
 * HeatTransferInSolidsAndFluids.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Oct 21 2023, 10:44 by COMSOL 6.0.0.318. */
public class HeatTransferInSolidsAndFluids {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\opt\\comsol\\java");

    model.component().create("comp1", false);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "cad");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "D:/opt/comsol/stp/origin/rgoh.stp");
    model.component("comp1").geom("geom1").feature("imp1").set("importtol", 1.0E-6);
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("ballsel1", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel1").set("posx", -0.01431);
    model.component("comp1").geom("geom1").feature("ballsel1").set("posy", 0.01311);
    model.component("comp1").geom("geom1").feature("ballsel1").set("posz", 0.005);
    model.component("comp1").geom("geom1").feature("ballsel1").set("r", 1.0E-8);
    model.component("comp1").geom("geom1").create("ballsel2", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("ballsel2").set("posx", -0.02511);
    model.component("comp1").geom("geom1").feature("ballsel2").set("posy", 0.01283);
    model.component("comp1").geom("geom1").feature("ballsel2").set("posz", 0.005);
    model.component("comp1").geom("geom1").feature("ballsel2").set("r", 1.0E-8);
    model.component("comp1").geom("geom1").create("ballsel3", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("ballsel3").set("posx", -0.03604);
    model.component("comp1").geom("geom1").feature("ballsel3").set("posy", 0.00964);
    model.component("comp1").geom("geom1").feature("ballsel3").set("posz", 0.005);
    model.component("comp1").geom("geom1").feature("ballsel3").set("r", 1.0E-8);
    model.component("comp1").geom("geom1").create("ballsel4", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("ballsel4").set("posx", 0.00553);
    model.component("comp1").geom("geom1").feature("ballsel4").set("posy", -3.3E-4);
    model.component("comp1").geom("geom1").feature("ballsel4").set("posz", 0.005);
    model.component("comp1").geom("geom1").feature("ballsel4").set("r", 1.0E-8);
    model.component("comp1").geom("geom1").create("ballsel5", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel5").set("posx", -0.00876);
    model.component("comp1").geom("geom1").feature("ballsel5").set("posy", 0.00723);
    model.component("comp1").geom("geom1").feature("ballsel5").set("posz", 0.005);
    model.component("comp1").geom("geom1").feature("ballsel5").set("r", 1.0E-8);
    model.component("comp1").geom("geom1").create("ballsel6", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel6").set("posx", 0.02571);
    model.component("comp1").geom("geom1").feature("ballsel6").set("posy", 0.0101);
    model.component("comp1").geom("geom1").feature("ballsel6").set("posz", 0.013);
    model.component("comp1").geom("geom1").feature("ballsel6").set("r", 1.0E-8);
    model.component("comp1").geom("geom1").create("ballsel7", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel7").set("posx", 0.025);
    model.component("comp1").geom("geom1").feature("ballsel7").set("posy", 0.00776);
    model.component("comp1").geom("geom1").feature("ballsel7").set("posz", 0.013);
    model.component("comp1").geom("geom1").feature("ballsel7").set("r", 1.0E-8);
    model.component("comp1").geom("geom1").create("ballsel8", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel8").set("posx", -0.01166);
    model.component("comp1").geom("geom1").feature("ballsel8").set("posy", 0.0086);
    model.component("comp1").geom("geom1").feature("ballsel8").set("posz", 0.005);
    model.component("comp1").geom("geom1").feature("ballsel8").set("r", 1.0E-8);
    model.component("comp1").geom("geom1").feature("fin").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("fin").set("repairtol", 1.2E-6);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat1").selection().named("geom1_ballsel5");
    model.component("comp1").material("mat2").selection().named("geom1_ballsel6");

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().named("geom1_ballsel1");

    model.component("comp1").physics("ht").create("co1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("co1").selection().named("geom1_ballsel2");
    model.component("comp1").physics("ht").create("star1", "SurfaceToAmbientRadiation", 2);
    model.component("comp1").physics("ht").feature("star1").selection().named("geom1_ballsel3");
    model.component("comp1").physics("ht").create("i1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("i1").selection().named("geom1_ballsel4");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("geom1_ballsel7");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().named("geom1_ballsel8");

    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"223", "0", "0", "0", "223", "0", "0", "0", "223"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2756");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "905");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"253", "0", "0", "0", "253", "0", "0", "0", "253"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "3526");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "758");

    model.component("comp1").physics("ht").feature("hs1").set("Q0", "5.9e6");
    model.component("comp1").physics("ht").feature("star1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("star1").set("epsilon_rad", 0.5);
    model.component("comp1").physics("ht").feature("i1").set("Tustr", "283.15[K]");

    model.component("comp1").mesh("mesh1").feature("size").set("custom", "on");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.001);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", "on");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmin", 0.002);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", "on");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmin", 0.002);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,5)");

    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,0.1,5)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "Default");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
    model.sol("sol1").feature("t1").set("atolmethod", new String[]{"comp1_T", "global"});
    model.sol("sol1").feature("t1").set("atol", new String[]{"comp1_T", "1e-3"});
    model.sol("sol1").feature("t1").set("atolvaluemethod", new String[]{"comp1_T", "factor"});
    model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
    model.sol("sol1").feature("t1").set("estrat", "exclude");
    model.sol("sol1").feature("t1").set("maxorder", 2);
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 0.9);
    model.sol("sol1").feature("t1").feature("fc1").set("stabacc", "aacc");
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdim", 5);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccmix", 0.9);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdelay", 1);
    model.sol("sol1").feature("t1").create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("d1").label("\u76f4\u63a5\uff0c\u4f20\u70ed\u53d8\u91cf (ht)");
    model.sol("sol1").feature("t1").create("i1", "Iterative");
    model.sol("sol1").feature("t1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("t1").feature("i1").set("prefuntype", "left");
    model.sol("sol1").feature("t1").feature("i1").set("itrestart", 50);
    model.sol("sol1").feature("t1").feature("i1").set("rhob", 20);
    model.sol("sol1").feature("t1").feature("i1").set("maxlinit", 10000);
    model.sol("sol1").feature("t1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("t1").feature("i1").label("AMG\uff0c\u4f20\u70ed\u53d8\u91cf (ht)");
    model.sol("sol1").feature("t1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("mgcycle", "v");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("strconn", 0.01);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("nullspace", "constant");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("usesmooth", false);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("loweramg", true);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("so1").set("iter", 2);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("so1").set("iter", 2);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.9);
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol1").feature("t1").feature("fc1").set("damp", 0.9);
    model.sol("sol1").feature("t1").feature("fc1").set("stabacc", "aacc");
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdim", 5);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccmix", 0.9);
    model.sol("sol1").feature("t1").feature("fc1").set("aaccdelay", 1);
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").feature().create("iso1", "Isosurface");
    model.result("pg2").feature("iso1").label("\u7b49\u503c\u9762");
    model.result("pg2").feature("iso1").set("number", 10);
    model.result("pg2").feature("iso1").set("levelrounding", false);
    model.result("pg2").feature("iso1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("iso1").set("data", "parent");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
