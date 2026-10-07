package com.freshlink.dto;

public record QualityCheckRequest(
    String imageBase64,
    String mimeType,
    String cropType,
    String role
) {}