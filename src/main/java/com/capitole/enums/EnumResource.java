package com.capitole.enums;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EnumResource implements Serializable {

   @EqualsAndHashCode.Include
   @NotBlank
   private String id;

   @EqualsAndHashCode.Exclude
   private String description;

   public EnumResource(String id) {
      this.id = id;
   }

}
