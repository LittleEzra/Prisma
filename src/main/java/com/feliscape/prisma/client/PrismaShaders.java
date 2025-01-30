package com.feliscape.prisma.client;

import com.feliscape.prisma.Prisma;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.ShaderDefines;
import net.minecraft.client.renderer.ShaderProgram;

import java.util.ArrayList;
import java.util.List;

public class PrismaShaders {
    private static final List<ShaderProgram> PROGRAMS = new ArrayList();

    public static final ShaderProgram RENDERTYPE_AURORA = register("rendertype_aurora", DefaultVertexFormat.POSITION);

    public static List<ShaderProgram> getProgramsToPreload() {
        return PROGRAMS;
    }

    private static ShaderProgram register(String name, VertexFormat vertexFormat) {
        return register(name, vertexFormat, ShaderDefines.EMPTY);
    }
    private static ShaderProgram register(String name, VertexFormat vertexFormat, ShaderDefines shaderDefines) {
        ShaderProgram shaderprogram = new ShaderProgram(Prisma.asResource("core/" + name), vertexFormat, shaderDefines);
        PROGRAMS.add(shaderprogram);
        return shaderprogram;
    }
}
