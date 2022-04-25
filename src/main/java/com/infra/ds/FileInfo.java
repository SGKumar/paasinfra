package com.atlassianold.ds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FileInfo {
  @EqualsAndHashCode.Include
  private String fileName;
  private String dirName;
  private int fileSize;
}
