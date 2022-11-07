package com.capitole.security;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContent implements Serializable {

   private String userEmail;

   private Long roleId;

   private String roleCategory;

   private Boolean anonymous;

   private String timeZone;

   private String language;

   private List<Integer> resourceList;

}
