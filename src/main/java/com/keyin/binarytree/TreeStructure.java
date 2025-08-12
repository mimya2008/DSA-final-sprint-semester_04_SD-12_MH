package com.keyin.binarytree;
import jakarta.persistence.*;

@Entity
public class TreeStructure {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String treeJson;

    @Column(columnDefinition = "TEXT")
    private String userInputs;

    public TreeStructure() {}
    public TreeStructure(String treeJson, String userInputs) {
        this.treeJson = treeJson; this.userInputs = userInputs;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTreeJson() { return treeJson; }
    public void setTreeJson(String treeJson) { this.treeJson = treeJson; }
    public String getUserInputs() { return userInputs; }
    public void setUserInputs(String userInputs) { this.userInputs = userInputs; }
}

