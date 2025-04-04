#version 330 core

out vec4 FragColor;

in vec2 TexCoord; // Recevez les coordonnées de texture du vertex shader

uniform sampler2D texture1; // Texture sampler



void main() {
    vec4 texColor = texture(texture1, TexCoord); // Utilisez les coordonnées de texture

    FragColor = texColor;
}
