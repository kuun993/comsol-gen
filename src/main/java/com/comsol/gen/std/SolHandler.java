package com.comsol.gen.std;

import com.comsol.gen.util.TagUtil;
import com.comsol.model.Model;
import com.comsol.model.SolverFeature;
import com.comsol.model.SolverSequence;

/**
 * @author waani
 * @date 2023/10/31 09:14
 * @email kuun993@163.com
 * @description TODO
 */
public class SolHandler {


    /**
     * 固体传热瞬态默认求解器
     * @param model
     * @param stdTag
     */
    public void createHTTimeSolver(Model model, String stdTag) {
        
        String solTag = TagUtil.uniqueTag("sol");

        SolverSequence sol = model.sol().create(solTag);
        sol.study(stdTag);

        // 编译方程
        String stTag = TagUtil.uniqueTag("st");
        SolverFeature studyStep = sol.create(stTag, "StudyStep");
        // 使用研究
        studyStep.set("study", stdTag);
        // 使用研究步骤
        studyStep.set("studystep", "time");

        // 因变量
        String vTag = TagUtil.uniqueTag("v");
        SolverFeature variables = sol.create(vTag, "Variables");
        variables.set("control", "time");
        
        // 瞬态求解器
        String tTag = TagUtil.uniqueTag("t");
        SolverFeature time = sol.create(tTag, "Time");
        time.set("tlist", "range(0,0.1,1.3)");
        time.set("plot", "off");
        time.set("plotgroup", "pg1");
        time.set("plotfreq", "tout");
        time.set("probesel", "all");
        time.set("probes", new String[]{});
        time.set("probefreq", "tsteps");
        time.set("atolglobalvaluemethod", "factor");
        time.set("atolmethod", new String[]{"comp1_T", "global"});
        time.set("atol", new String[]{"comp1_T", "1e-3"});
        time.set("atolvaluemethod", new String[]{"comp1_T", "factor"});
        time.set("endtimeinterpolation", true);
        time.set("estrat", "exclude");
        time.set("maxorder", 2);
        time.set("control", "time");

        // 全耦合
        String fcTag = TagUtil.uniqueTag("fc");
        SolverFeature fullyCoupled = time.create(fcTag, "FullyCoupled");
        fullyCoupled.set("jtech", "once");
        fullyCoupled.set("damp", 0.9);
        fullyCoupled.set("stabacc", "aacc");
        fullyCoupled.set("aaccdim", 5);
        fullyCoupled.set("aaccmix", 0.9);
        fullyCoupled.set("aaccdelay", 1);

        // 直接，传热变量 (ht)
        String dTag = TagUtil.uniqueTag("d");
        SolverFeature direct = time.create(dTag, "Direct");
        direct.set("linsolver", "pardiso");
        direct.set("pivotperturb", 1.0E-13);
        direct.label("Direct (ht)");
        
        // AMG，传热变量 (ht)
        String iTag = TagUtil.uniqueTag("i");
        SolverFeature iterative = time.create(iTag, "Iterative");
        iterative.set("linsolver", "gmres");
        iterative.set("prefuntype", "left");
        iterative.set("itrestart", 50);
        iterative.set("rhob", 20);
        iterative.set("maxlinit", 10000);
        iterative.set("nlinnormuse", "on");
        iterative.label("AMG Iterative (ht)");
        // 多重网格
        String mgTag = TagUtil.uniqueTag("mg");
        SolverFeature solverFeature = iterative.create(mgTag, "Multigrid");
        solverFeature.set("prefun", "saamg");
        solverFeature.set("mgcycle", "v");
        solverFeature.set("maxcoarsedof", 50000);
        solverFeature.set("strconn", 0.01);
        solverFeature.set("nullspace", "constant");
        solverFeature.set("usesmooth", false);
        solverFeature.set("saamgcompwise", true);
        solverFeature.set("loweramg", true);
        // 预平滑器
        solverFeature.feature("pr").create("so1", "SOR");
        solverFeature.feature("pr").feature("so1").set("iter", 2);
        solverFeature.feature("pr").feature("so1").set("relax", 0.9);
        // 后平滑器
        solverFeature.feature("po").create("so1", "SOR");
        solverFeature.feature("po").feature("so1").set("iter", 2);
        solverFeature.feature("po").feature("so1").set("relax", 0.9);
        // 粗化求解器
        solverFeature.feature("cs").create("d1", "Direct");
        solverFeature.feature("cs").feature("d1").set("linsolver", "pardiso");
        solverFeature.feature("cs").feature("d1").set("pivotperturb", 1.0E-13);

        // 全耦合
        fullyCoupled.set("linsolver", dTag);
        fullyCoupled.set("jtech", "once");
        fullyCoupled.set("damp", 0.9);
        fullyCoupled.set("stabacc", "aacc");
        fullyCoupled.set("aaccdim", 5);
        fullyCoupled.set("aaccmix", 0.9);
        fullyCoupled.set("aaccdelay", 1);
        time.feature().remove("fcDef");

        sol.attach(stdTag);
    }

    public void createSHTTimeSolver(Model model, String stdTag) {

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
        model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
        model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*1.0099564347411227");
        model.sol("sol1").feature("v1").set("control", "time");
        model.sol("sol1").create("t1", "Time");
        model.sol("sol1").feature("t1").set("tlist", "range(0,0.1,1)");
        model.sol("sol1").feature("t1").set("plot", "off");
        model.sol("sol1").feature("t1").set("plotgroup", "Default");
        model.sol("sol1").feature("t1").set("plotfreq", "tout");
        model.sol("sol1").feature("t1").set("probesel", "all");
        model.sol("sol1").feature("t1").set("probes", new String[]{});
        model.sol("sol1").feature("t1").set("probefreq", "tsteps");
        model.sol("sol1").feature("t1").set("rtol", 0.001);
        model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "factor");
        model.sol("sol1").feature("t1").set("atolmethod", new String[]{"comp1_T", "global", "comp1_u", "global"});
        model.sol("sol1").feature("t1").set("atol", new String[]{"comp1_T", "1e-3", "comp1_u", "1e-3"});
        model.sol("sol1").feature("t1").set("atolvaluemethod", new String[]{"comp1_T", "factor", "comp1_u", "factor"});
        model.sol("sol1").feature("t1").set("endtimeinterpolation", true);
        model.sol("sol1").feature("t1").set("estrat", "exclude");
        model.sol("sol1").feature("t1").set("maxorder", 2);
        model.sol("sol1").feature("t1").set("control", "time");
        model.sol("sol1").feature("t1").feature("aDef").set("cachepattern", true);
        model.sol("sol1").feature("t1").create("se1", "Segregated");
        model.sol("sol1").feature("t1").feature("se1").feature().remove("ssDef");
        model.sol("sol1").feature("t1").feature("se1").create("ss1", "SegregatedStep");
        model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_T"});
        model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subdamp", 1);
        model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subjtech", "once");
        model.sol("sol1").feature("t1").create("d1", "Direct");
        model.sol("sol1").feature("t1").feature("d1").set("linsolver", "pardiso");
        model.sol("sol1").feature("t1").feature("d1").set("pivotperturb", 1.0E-13);
        model.sol("sol1").feature("t1").feature("d1").label("\u76f4\u63a5\uff0c\u4f20\u70ed\u53d8\u91cf ht (te1)");
        model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("linsolver", "d1");
        model.sol("sol1").feature("t1").feature("se1").feature("ss1").label("\u6e29\u5ea6");
        model.sol("sol1").feature("t1").feature("se1").create("ss2", "SegregatedStep");
        model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("segvar", new String[]{"comp1_u"});
        model.sol("sol1").feature("t1").create("d2", "Direct");
        model.sol("sol1").feature("t1").feature("d2").set("linsolver", "pardiso");
        model.sol("sol1").feature("t1").feature("d2").set("pivotperturb", 1.0E-9);
        model.sol("sol1").feature("t1").feature("d2")
                .label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 solid (te1)");
        model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("linsolver", "d2");
        model.sol("sol1").feature("t1").feature("se1").feature("ss2").label("\u56fa\u4f53\u529b\u5b66");
        model.sol("sol1").feature("t1").feature("se1").set("segstabacc", "segaacc");
        model.sol("sol1").feature("t1").feature("se1").set("segaaccdim", 5);
        model.sol("sol1").feature("t1").feature("se1").set("segaaccmix", 0.9);
        model.sol("sol1").feature("t1").feature("se1").set("segaaccdelay", 0);
        model.sol("sol1").feature("t1").feature("se1").create("ll1", "LowerLimit");
        model.sol("sol1").feature("t1").feature("se1").feature("ll1").set("lowerlimit", "comp1.T 0 ");
        model.sol("sol1").feature("t1").create("i1", "Iterative");
        model.sol("sol1").feature("t1").feature("i1").set("linsolver", "gmres");
        model.sol("sol1").feature("t1").feature("i1").set("rhob", 400);
        model.sol("sol1").feature("t1").feature("i1").set("nlinnormuse", true);
        model.sol("sol1").feature("t1").feature("i1")
                .label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 solid (te1)");
        model.sol("sol1").feature("t1").feature("i1").create("mg1", "Multigrid");
        model.sol("sol1").feature("t1").feature("i1").feature("mg1").set("prefun", "gmg");
        model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
        model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
        model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
        model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
        model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
        model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
                .set("linsolver", "pardiso");
        model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("cs").feature("d1")
                .set("pivotperturb", 1.0E-9);
        model.sol("sol1").feature("t1").create("i2", "Iterative");
        model.sol("sol1").feature("t1").feature("i2").set("linsolver", "gmres");
        model.sol("sol1").feature("t1").feature("i2").set("prefuntype", "left");
        model.sol("sol1").feature("t1").feature("i2").set("itrestart", 50);
        model.sol("sol1").feature("t1").feature("i2").set("rhob", 20);
        model.sol("sol1").feature("t1").feature("i2").set("maxlinit", 10000);
        model.sol("sol1").feature("t1").feature("i2").set("nlinnormuse", "on");
        model.sol("sol1").feature("t1").feature("i2").label("AMG\uff0c\u4f20\u70ed\u53d8\u91cf ht (te1)");
        model.sol("sol1").feature("t1").feature("i2").create("mg1", "Multigrid");
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("prefun", "saamg");
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("mgcycle", "v");
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("maxcoarsedof", 50000);
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("strconn", 0.01);
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("nullspace", "constant");
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("usesmooth", false);
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("saamgcompwise", true);
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("loweramg", true);
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").create("so1", "SOR");
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("so1").set("iter", 2);
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("pr").feature("so1").set("relax", 0.9);
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").create("so1", "SOR");
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("so1").set("iter", 2);
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("po").feature("so1").set("relax", 0.9);
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("cs").feature("d1")
                .set("linsolver", "pardiso");
        model.sol("sol1").feature("t1").feature("i2").feature("mg1").feature("cs").feature("d1")
                .set("pivotperturb", 1.0E-13);
        model.sol("sol1").feature("t1").feature().remove("fcDef");
        model.sol("sol1").attach("std1");
    }


}
