package org.rendara.shader;

public class SimpleShader {

    Program program;

    public SimpleShader() {
        ShaderBuilder builder = new ShaderBuilder();
        builder.version(330, "core")
                .out("vec4", "FragColor")
                .in("vec2", "TexCoord")
                .uniform("sampler2D", "texture1")
                .main()
                .append("vec4 texColor = texture(texture1, TexCoord);")
                .append("FragColor = texColor;")
                .end();

        String fragmentSource = builder.build();
        builder.clear();
        builder.version(330, "core")
                .layout("location = 0")
                .in("vec3", "aPos")
                .in("vec2", "aTexCoord")
                .out("vec2", "TexCoord")
                .uniform("mat4", "model")
                .uniform("mat4", "view")
                .uniform("mat4", "projection")
                .main()
                .append("gl_Position = projection * view * model * vec4(aPos, 1.0);")
                .append("TexCoord = aTexCoord;")
                .end();
        String vertexSource = builder.build();
        builder.clear();
        Shader vertexShader = new Shader(vertexSource, ShaderKind.SHADER_KIND_VERTEX);
        Shader fragmentShader = new Shader(fragmentSource, ShaderKind.SHADER_KIND_FRAGMENT);
        Program program = new Program();
        program.attach(vertexShader);
        program.attach(fragmentShader);
        program.link();


        this.program = program;
    }

    public Program getProgram() {
        return program;
    }
}
