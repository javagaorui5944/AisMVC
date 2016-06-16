package org.aisframework.web.asm;

/**
 * Created by lenovo on 2016/5/19.
 */

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.List;

public class ReadMethodArgNameMethodVisitor extends MethodVisitor {

    public List<String> argumentNames;

    public int argLen;

    public ReadMethodArgNameMethodVisitor(int api) {
        super(api);
    }

    @Override
    public void visitLocalVariable(String name, String desc, String signature,
                                   Label start, Label end, int index) {
        if("this".equals(name)) {
            return;
        }
        if(argLen-- > 0) {
            argumentNames.add(name);
        }
    }

}
