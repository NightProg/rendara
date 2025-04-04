#version 330 core

layout(location = 0) in vec3 position; // Position des vertices
layout(location = 1) in vec2 texCoord; // Coordonnées de texture

out vec2 TexCoord; // Variable à transmettre au shader fragment

uniform mat4 model;      // Matrice de transformation du modèle
uniform mat4 view;       // Matrice de vue de la caméra
uniform mat4 projection;  // Matrice de projection

void main()
{
    // Calcul de la position finale du vertex
    gl_Position = projection * view * model * vec4(position, 1.0);

    // Passer les coordonnées de texture au shader fragment
    TexCoord = texCoord;
}