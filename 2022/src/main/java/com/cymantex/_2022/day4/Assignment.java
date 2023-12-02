package com.cymantex._2022.day4;

class Assignment {

  private final int lowSectionId;
  private final int highSectionId;

  public Assignment(String sectionIdRange) {
    String[] sectionIds = sectionIdRange.split("-");
    lowSectionId = Integer.parseInt(sectionIds[0]);
    highSectionId = Integer.parseInt(sectionIds[1]);
  }

  public boolean contains(Assignment assignment) {
    return lowSectionId >= assignment.lowSectionId &&
        highSectionId <= assignment.highSectionId;
  }

  public boolean hasOverlapWith(Assignment assignment) {
    return (assignment.lowSectionId >= lowSectionId &&
        assignment.lowSectionId <= highSectionId) ||
        (assignment.highSectionId >= lowSectionId &&
            assignment.highSectionId <= highSectionId);
  }
}
