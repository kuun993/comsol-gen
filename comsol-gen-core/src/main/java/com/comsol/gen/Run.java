package com.comsol.gen;

import com.alibaba.fastjson.JSON;
import com.comsol.gen.comp.ModelHandler;
import com.comsol.gen.enums.PhysicsEnum;
import com.comsol.gen.enums.StudyEnum;
import com.comsol.gen.geom.GeomHandler;
import com.comsol.gen.mat.MaterialHandler;
import com.comsol.gen.mesh.MeshHandler;
import com.comsol.gen.physics.PhysicsHandler;
import com.comsol.gen.result.ResultHandler;
import com.comsol.gen.std.SolverHandler;
import com.comsol.gen.std.StudyHandler;
import com.comsol.gen.util.TagUtil;
import com.comsol.gen.vo.*;
import com.comsol.model.GeomSequence;
import com.comsol.model.Model;
import com.comsol.model.ModelNode;
import com.comsol.model.Study;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


/**
 * @author waani
 * @date 2023/11/9 17:26
 * @email kuun993@163.com
 * @description TODO
 */
public class Run {



    private static JdbcTemplate jdbcTemplate;


    private static final String SELECT_SQL = "SELECT id, task_id, content, create_time FROM task_info WHERE task_id=?;";


    private static JdbcTemplate getJdbcTemplate(Properties properties) {
        if (jdbcTemplate != null) {
            return jdbcTemplate;
        }
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("ds.driver"));
        config.setJdbcUrl(properties.getProperty("ds.url"));
        config.setUsername(properties.getProperty("ds.username"));
        config.setPassword(properties.getProperty("ds.password"));
        DataSource dataSource = new HikariDataSource(config);
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }


    private static Model run(ComsolVo vo) {

        // 模型组件
        ModelHandler modelHandler = new ModelHandler();
        Model model = modelHandler.build(vo.getModelName(), vo.getModelPath());
        // 组件tag
        String compTag = TagUtil.compTag();
        ModelNode modelNode = modelHandler.createComponent(model, compTag);

        // 几何
        GeomHandler geomHandler = new GeomHandler();
        GeomSequence geom = geomHandler.geomImport(modelNode, vo.getGeom());


        // 物理场
        PhysicsHandler physicsHandler = new PhysicsHandler();
        physicsHandler.create(modelNode, geom, vo.getPhysics());

        // 材料
        MaterialHandler materialHandler = new MaterialHandler();
        materialHandler.create(modelNode, geom, vo.getMaterials());

        // 网格
        MeshHandler meshHandler = new MeshHandler();
        meshHandler.create(modelNode, geom, vo.getMeshes());

        // 研究
        StudyHandler studyHandler = new StudyHandler();
        Study study = studyHandler.createStudy(model, StudyEnum.Transient, PhysicsEnum.HeatTransfer);

        // 求解
        SolverHandler solverHandler = new SolverHandler();
        solverHandler.createSolver(model, study.tag(), StudyEnum.Transient, PhysicsEnum.HeatTransfer);

        // 结果
        ResultHandler resultHandler = new ResultHandler();
        resultHandler.export(model, vo.getResult());

        return model;
    }



    private static ComsolVo selectParam(String taskId, Properties properties) {
        TaskInfo taskInfo = getJdbcTemplate(properties).queryForObject(SELECT_SQL, new BeanPropertyRowMapper<>(TaskInfo.class), taskId);
        String content;
        if (taskInfo == null || (content = taskInfo.getContent()) == null) {
            return null;
        }
        return JSON.parseObject(content, ComsolVo.class);
    }



    @SneakyThrows
    private static Properties properties(String path) {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get(path))) {
            properties.load(inputStream);
        }
        return properties;
    }



    public static Model start(String taskId, String configPath) {
        Properties properties = properties(configPath);
        ComsolVo comsolVo = selectParam(taskId, properties);
        if (comsolVo == null) {
            return null;
        }
        return Run.run(comsolVo);
    }


}
