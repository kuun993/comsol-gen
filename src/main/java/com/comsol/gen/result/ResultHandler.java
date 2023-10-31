package com.comsol.gen.result;

import com.comsol.gen.util.TagUtil;
import com.comsol.gen.vo.ResultVo;
import com.comsol.model.ExportFeature;
import com.comsol.model.Model;
import com.comsol.model.ResultFeature;

/**
 * @author waani
 * @date 2023/10/30 15:33
 * @email kuun993@163.com
 * @description 结果导出等
 */
public class ResultHandler {
    
    
    public void export(Model model, ResultVo resultVo) {
        // 构建 3D 绘图
        String pgTag = buildPlotGroup3D(model, resultVo);

        // 导出数据
        exportData(model, resultVo);

        // 导出图像
        exportImage(model, resultVo, pgTag);
    }


    /**
     * 导出数据
     * @param model
     * @param resultVo
     */
    private void exportData(Model model, ResultVo resultVo) {
        String dataTag = TagUtil.uniqueTag("data");
        ExportFeature exportFeature = model.result().export().create(dataTag, "Data");
        exportFeature.set("exporttype", resultVo.getExportType());
        exportFeature.set("filename", resultVo.getDataFilePath());
        exportFeature.run();
    }


    /**
     * 导出图像
     * @param model
     * @param resultVo
     */
    private void exportImage(Model model, ResultVo resultVo, String pgTag) {
        String imgTag = TagUtil.uniqueTag("img");
        ExportFeature exportFeatureTag = model.result().export().create(imgTag, "Image");
        // 固化默认参数
        exportFeatureTag.set("size", "current");
        exportFeatureTag.set("unit", "px");
        exportFeatureTag.set("height", "600");
        exportFeatureTag.set("width", "800");
        exportFeatureTag.set("lockratio", "off");
        exportFeatureTag.set("resolution", "96");
        exportFeatureTag.set("antialias", "on");
        exportFeatureTag.set("zoomextents", "on");
        exportFeatureTag.set("fontsize", "9");
        exportFeatureTag.set("colortheme", "globaltheme");
        exportFeatureTag.set("customcolor", new double[]{1, 1, 1});
        exportFeatureTag.set("background", "color");
        exportFeatureTag.set("gltfincludelines", "on");
        exportFeatureTag.set("title1d", "on");
        exportFeatureTag.set("legend1d", "on");
        exportFeatureTag.set("logo1d", "on");
        exportFeatureTag.set("options1d", "on");
        exportFeatureTag.set("title2d", "on");
        exportFeatureTag.set("legend2d", "on");
        exportFeatureTag.set("logo2d", "on");
        exportFeatureTag.set("options2d", "off");
        exportFeatureTag.set("title3d", "on");
        exportFeatureTag.set("legend3d", "on");
        exportFeatureTag.set("logo3d", "on");
        exportFeatureTag.set("options3d", "on");
        exportFeatureTag.set("axisorientation", "on");
        exportFeatureTag.set("grid", "on");
        exportFeatureTag.set("axes1d", "on");
        exportFeatureTag.set("axes2d", "on");
        exportFeatureTag.set("showgrid", "on");
        exportFeatureTag.set("target", "file");
        exportFeatureTag.set("qualitylevel", "92");
        exportFeatureTag.set("qualityactive", "off");
        exportFeatureTag.set("imagetype", "png");
        exportFeatureTag.set("lockview", "off");
        // 选择绘图组
        exportFeatureTag.set("sourceobject", pgTag);
        // 导出的图片路径
        exportFeatureTag.set("pngfilename", resultVo.getImgFilePath());
        exportFeatureTag.run();

    }


    /**
     * 3D 绘图
     * @param model
     * @param resultVo
     */
    private String buildPlotGroup3D(Model model, ResultVo resultVo) {
        String pgTag = TagUtil.uniqueTag("pg");
        String volTag = TagUtil.uniqueTag("vol");
        ResultFeature resultFeature = model.result().create(pgTag, "PlotGroup3D");
        ResultFeature volume = resultFeature.create(volTag, "Volume");
        resultFeature.set("data", resultVo.getDatasetTag());
        volume.set("expr", resultVo.getExpression());
        resultFeature.run();
        return pgTag;
    }



}
