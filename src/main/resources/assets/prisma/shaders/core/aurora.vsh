#version 150

#moj_import <minecraft:light.glsl>
#moj_import <minecraft:fog.glsl>
#moj_import <minecraft:projection.glsl>

in vec3 Position;
in vec2 UV0;

uniform sampler2D Sampler2;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform vec3 ModelOffset;
uniform int FogShape;

out float vertexDistance;
out vec2 texCoord0;

void main() {
    vec3 pos = Position + ModelOffset;
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    vertexDistance = fog_distance(pos, FogShape);
    texCoord0 = UV0;
}