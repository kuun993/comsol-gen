package com.comsol.gen;

import com.comsol.gen.enums.InitEnum;
import com.comsol.gen.enums.StudyEnum;
import com.comsol.gen.comp.CompHandler;
import com.comsol.gen.comp.ModelHandler;
import com.comsol.model.Model;
import com.comsol.model.ModelNode;
import com.comsol.model.Study;
import com.comsol.model.StudyFeature;
import com.comsol.gen.enums.PhysicsInterfaceEnum;


/**
 * @author waani
 * @date 2023/10/7 10:52
 * @email kuun993@163.com
 * @description 模型初始化
 *
 * 1、空间维度：三维
 * 2、网格
 * 3、物理场接口：
 * @see PhysicsInterfaceEnum
 * 4、研究
 *
 * 实现以下四种物理场初始化
 * 1. RandomVibration(PSD) 随机振动模块
 * 2. Heat·Transferin·Solids·(ht) 固体传热模块
 * 3. Thermal-Structure·Interaction 热-结构相互作用模块
 * 4. Heat·Transferin·Solids and·Fluids(ht) 固体和流体传热模块·
 *
 *
 */
@Deprecated
public class ComsolInit {

    /**
     * 初始化
     * @param initEnum
     */
    public static Model init(InitEnum initEnum, String modelTag, String modelPath, String compTag) {
        ModelHandler modelHandler = new ModelHandler();
        Model model = modelHandler.build(modelTag, modelPath);
        switch (initEnum) {
            case RandomVibration: {
                ComsolInit.randomVibration(model, compTag);
                break;
            }
            case HeatTransferInSolidsTransient: {
                ComsolInit.heatTransferInSolids(model, StudyEnum.Transient, compTag);
                break;
            }
            case HeatTransferInSolidsStationary: {
                ComsolInit.heatTransferInSolids(model, StudyEnum.Stationary, compTag);
                break;
            }
            case ThermalStructureInteractionTransient: {
                ComsolInit.thermalStructureInteraction(model, StudyEnum.Transient, compTag);
                break;
            }
            case ThermalStructureInteractionStationary: {
                ComsolInit.thermalStructureInteraction(model, StudyEnum.Stationary, compTag);
                break;
            }
            case HeatTransferInSolidsAndFluidsTransient: {
                ComsolInit.heatTransferInSolidsAndFluids(model, StudyEnum.Transient, compTag);
                break;
            }
            case HeatTransferInSolidsAndFluidsStationary: {
                ComsolInit.heatTransferInSolidsAndFluids(model, StudyEnum.Stationary, compTag);
                break;
            }
            default: {

            }
        }
        return model;
    }

    /**
     * 随机振动
     * @param model
     */
    private static void randomVibration(Model model, String compTag) {
        // 1、初始化模型组件
        ModelNode comp = CompHandler.create(model, compTag);

        // 2、创建物理场接口
        createPhysics(PhysicsInterfaceEnum.SolidMechanics, comp);

        // 3、创建研究

        // 研究1
        Study std1 = model.study().create("std1");
        // 特征频率
        StudyFeature studyFeature1 = createStudyStep(std1, StudyEnum.EigenFrequency);

        // 研究2
        Study std2 = model.study().create("std2");
        // 模型降阶
        StudyFeature studyFeature2 = createStudyStep(std1, StudyEnum.ModelReduction);


        // 全局参数初始化，降阶建模

        // 全局降阶模型输入
        model.common().create("grmi1", "GlobalReducedModelInputs", "");
        // 频域，模式降阶模型
        model.reduced().create("rom1", "ModalFrequency");
        // 随机振动
        model.reduced().create("rvib1", "RandomVibration");
        // 频率响应模型
        model.reduced("rvib1").set("frequencyResponseModel", "rom1");

        // 研究1 默认设置
        studyFeature1.set("neigsactive", true);
        studyFeature1.set("neigs", 12);
        studyFeature1.set("shiftactive", true);
        studyFeature1.set("shift", "1");

        // 研究2 默认设置
        std2.setGenPlots(false);    // 生成默认绘图
        std2.setGenConv(false);     // 生成收敛图
        studyFeature2.set("trainingStudy", "std1");
        studyFeature2.set("trainingStep", StudyEnum.EigenFrequency.getTag());

        // 创建频域
        studyFeature2.feature().create("freq1", "Frequency");
        studyFeature2.feature("freq1").set("plist", "100");

        studyFeature2.set("unreducedModelStudy", "std2");
        studyFeature2.set("unreducedModelStep", "freq1");
        studyFeature2.set("romdata", "rom1");
    }

    /**
     * 固体传热
     * @param model
     */
    private static void heatTransferInSolids(Model model, StudyEnum studyEnum, String compTag) {
        // 1、初始化模型组件
        ModelNode comp = CompHandler.create(model, compTag);

        // 2、创建物理场接口
        createPhysics(PhysicsInterfaceEnum.HeatTransfer, comp);

        // 3、创建研究
        Study std1 = model.study().create("std1");
        StudyFeature studyStep = createStudyStep(std1, studyEnum);
        setSolveFor(studyStep, "/physics/ht");
    }

    /**
     * 热-结构相互作用
     * @param model
     */
    private static void thermalStructureInteraction(Model model, StudyEnum studyEnum, String compTag) {
        // 1、初始化模型组件
        ModelNode comp = CompHandler.create(model, compTag);

        // 2、创建物理场接口
        createPhysics(PhysicsInterfaceEnum.SolidMechanics, comp);
        comp.physics(PhysicsInterfaceEnum.SolidMechanics.getTag())
                .prop("StructuralTransientBehavior")
                .set("StructuralTransientBehavior", "Quasistatic");

        createPhysics(PhysicsInterfaceEnum.HeatTransfer, comp);

        // 热膨胀
        comp.multiphysics().create("te1", "ThermalExpansion", 3);
        comp.multiphysics("te1").set("Heat_physics", "ht");
        comp.multiphysics("te1").set("Solid_physics", "solid");
        comp.multiphysics("te1").selection().all();

        // 3、创建研究
        Study std1 = model.study().create("std1");
        StudyFeature studyStep = createStudyStep(std1, studyEnum);
        setSolveFor(studyStep, "/physics/solid", "/physics/ht", "/multiphysics/te1");
    }

    /**
     * 固体和流体传热
     * @param model
     */
    private static void heatTransferInSolidsAndFluids(Model model, StudyEnum studyEnum, String compTag) {
        // 1、初始化模型组件
        ModelNode comp = CompHandler.create(model, compTag);

        // 2、创建物理场接口
        createPhysics(PhysicsInterfaceEnum.HeatTransferInSolidsAndFluids, comp);

        // 3、创建研究
        Study std1 = model.study().create("std1");
        StudyFeature studyStep = createStudyStep(std1, studyEnum);
        setSolveFor(studyStep, "/physics/ht");
    }

    /**
     * 创建物理场接口
     * @param physics
     * @param comp
     */
    private static void createPhysics(PhysicsInterfaceEnum physics, ModelNode comp) {
        String geomTag = comp.geom().tag();
        comp.physics().create(physics.getTag(), physics.getPhysIntId(), geomTag);
    }

    /**
     * 创建研究步骤
     * @param study
     * @param studyEnum
     * @return
     */
    private static StudyFeature createStudyStep(Study study, StudyEnum studyEnum) {
        return study.create(studyEnum.getTag(), studyEnum.getType());
    }

    /**
     * setSolveFor
     * @param studyFeature
     * @param entityURIs
     */
    private static void setSolveFor(StudyFeature studyFeature, String ... entityURIs) {
        if(entityURIs == null || entityURIs.length == 0) {
            return;
        }
        for (String uri : entityURIs) {
            studyFeature.setSolveFor(uri, true);
        }
    }

}
