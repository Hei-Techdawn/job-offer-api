package com.example.initialapi.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseFile {
    private int id;
    private String name;
    private String url;
    private String type;
    private long size;
}
