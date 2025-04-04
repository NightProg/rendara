package org.rendara.shader;

public class ShaderBuilder {
    private StringBuilder source;

    public ShaderBuilder() {
        this.source = new StringBuilder();
    }

    public ShaderBuilder append(String line) {
        this.source.append(line).append("\n");
        return this;
    }

    public ShaderBuilder appendNoNewLine(String line) {
        this.source.append(line);
        return this;
    }

    public String build() {
        return this.source.toString();
    }

    public void clear() {
        this.source = new StringBuilder();
    }

    public ShaderBuilder version(int version, String profile) {
        this.append("#version " + version + " " + profile);
        return this;
    }

    public ShaderBuilder layout(String layout) {
        this.appendNoNewLine("layout(" + layout + ")");
        return this;
    }

    public ShaderBuilder in(String type, String name) {
        this.append("in " + type + " " + name + ";");
        return this;
    }

    public ShaderBuilder out(String type, String name) {
        this.append("out " + type + " " + name + ";");
        return this;
    }

    public ShaderBuilder uniform(String type, String name) {
        this.append("uniform " + type + " " + name + ";");
        return this;
    }

    public ShaderBuilder main() {
        this.append("void main() {");
        return this;
    }

    public ShaderBuilder end() {
        this.append("}");
        return this;
    }

    public ShaderBuilder set(String name, String value) {
        this.append(name + " = " + value + ";");
        return this;
    }




}
