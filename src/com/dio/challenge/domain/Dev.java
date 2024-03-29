package com.dio.challenge.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Dev {
    private String name;
    private Set<Content> subscribedContent = new LinkedHashSet<>();
    private Set<Content> completedContent = new LinkedHashSet<>();
    private Set<Content> subscribedShortCourse = new LinkedHashSet<> ();

    public void registerBootcamp(Bootcamp bootcamp) {
        this.subscribedContent.addAll(bootcamp.getContents());
        bootcamp.getSubscribedDevs().add(this);
    }

    public void registerShortCourse(ShortCourse shortCourse) {
        this.subscribedShortCourse.addAll(shortCourse.getContents());
        shortCourse.getRegisteredDevs().add(this);
    }

    public void progress() {
        Optional<Content> content = this.subscribedContent.stream().findFirst();
        if(content.isPresent()) {
            this.completedContent.add(content.get());
            this.subscribedContent.remove(content.get());
        } else {
            System.err.println("You are not enrolled in any content!");
        }
    }

    public double calculateTotalXp() {
        return this.completedContent
                .stream()
                .mapToDouble(Content::calculateXp)
                .sum();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Content> getSubscribedContent() {
        return subscribedContent;
    }

    public void setSubscribedContent(Set<Content> subscribedContent) {
        this.subscribedContent = subscribedContent;
    }

    public Set<Content> getCompletedContent() {
        return completedContent;
    }

    public void setCompletedContent(Set<Content> completedContent) {
        this.completedContent = completedContent;
    }

    public Set<Content> getSubscribedShortCourse() {
        return subscribedShortCourse;
    }

    public void setSubscribedShortCourse(Set<Content> subscribedShortCourse) {
        this.subscribedShortCourse = subscribedShortCourse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(name, dev.name) && Objects.equals(subscribedContent, dev.subscribedContent) && Objects.equals(completedContent, dev.completedContent) && Objects.equals(subscribedShortCourse, dev.subscribedShortCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subscribedContent, completedContent, subscribedShortCourse);
    }
}
