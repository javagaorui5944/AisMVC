package org.aisframework.web.asm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ReadMethodArgNameClassVisitor extends ClassVisitor {

    public Map<String, List<String>> nameArgMap = new HashMap<String, List<String>>();

    public ReadMethodArgNameClassVisitor() {
        super(Opcodes.ASM5);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {
        Type methodType = Type.getMethodType(desc);
        int len = methodType.getArgumentTypes().length;
        List<String> argumentNames = new ArrayList<String>();
        nameArgMap.put(name, argumentNames);
        ReadMethodArgNameMethodVisitor visitor = new ReadMethodArgNameMethodVisitor(Opcodes.ASM5);
        visitor.argumentNames = argumentNames;
        visitor.argLen = len;
        return visitor;
    }
}
