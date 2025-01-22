package com.yoecode.features_springboot.exampleUploadAndGenerateArchives.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "documents")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String format;

    @Lob  // Se utiliza @Lob para indicar que es un tipo de dato grande (BLOB)
    @Column(nullable = false, columnDefinition = "MEDIUMBLOB")
    private byte[] content;
}
