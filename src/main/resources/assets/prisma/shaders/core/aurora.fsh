#version 150

#moj_import <minecraft:fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform float GameTime;

in float vertexDistance;
in vec2 texCoord0;

out vec4 fragColor;

const vec4[3] colors = vec4[](
    vec4(0.92, 0.4, 0.58, 0.6),
    vec4(0.46, 0.45, 0.79, 0.6),
    vec4(0.35, 0.98, 0.4, 0.6)
);
const float PI = 3.14159265;
const float TAU = PI * 2.0;

float fresnel(vec2 uv){
    vec2 toCenter = (vec2(0.5, 0.5) - uv);
    float f = 1.0 - sqrt(toCenter.x * toCenter.x + toCenter.y * toCenter.y) * 2.0;
    return f;
}

void main() {
    vec4 original_color = texture(Sampler0, texCoord0 + vec2(GameTime * 32.0, 0)) * ColorModulator;
    vec4 color = vec4(1.0, 1.0, 1.0, original_color.r * fresnel(texCoord0));
    //vec4 color = vec4(normalized, normalized, normalized, 1.0);

    #ifdef ALPHA_CUTOUT
    if (color.a < ALPHA_CUTOUT) {
        discard;
    }
    #endif
    fragColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);
}
