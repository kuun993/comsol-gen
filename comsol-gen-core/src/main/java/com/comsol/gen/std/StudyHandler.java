package com.comsol.gen.std;

import com.comsol.gen.enums.PhysicsEnum;
import com.comsol.gen.enums.StudyEnum;
import com.comsol.gen.util.TagUtil;
import com.comsol.model.Model;
import com.comsol.model.Study;
import com.comsol.model.StudyFeature;

/**
 * @author waani
 * @date 2023/11/6 15:31
 * @email kuun993@163.com
 * @description TODO
 */
public class StudyHandler {



    public Study createStudy(Model model, StudyEnum studyEnum, PhysicsEnum physicsEnum) {
        String stdTag = TagUtil.stdTag();

        Study study = model.study().create(stdTag);

        StudyFeature studyFeature = setStudy(study, studyEnum);

        setSolveFor(studyFeature, physicsEnum);


        return study;
    }


    private StudyFeature setStudy(Study study, StudyEnum studyEnum) {
        return study.create(studyEnum.getTag(), studyEnum.getType());
    }


    private void setSolveFor(StudyFeature studyFeature, PhysicsEnum physicsEnum) {
        String[] solveFor = physicsEnum.getSolveFor();
        if (solveFor == null || solveFor.length == 0) {
            return;
        }
        for (String s : solveFor) {
            studyFeature.setSolveFor(s, true);
        }
    }


}
