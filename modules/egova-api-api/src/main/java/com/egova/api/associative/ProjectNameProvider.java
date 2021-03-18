package com.egova.api.associative;

import com.egova.api.entity.codes.ProjectIntro;
import com.egova.associative.AssociativeProvider;
import org.springframework.stereotype.Component;

/**
 * @author chendb
 * @description: 数字字典联想
 * @date 2020-04-21 19:50:13
 */
@Component
public class ProjectNameProvider implements AssociativeProvider {


    @Override
    public Object associate(Object key) {
        if (key != null) {
            ProjectIntro intro = new ProjectIntro(key.toString());
            return intro.getText();
        }
        return null;
    }
}
