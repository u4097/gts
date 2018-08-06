package ru.panmin.gtspro.ui.blocks.model;

public class Block {
    private BlockType.Type type;
    private Integer size;
    private Boolean selected = false;

    public Block(BlockType.Type type, Integer size, Boolean selected) {
        this.type = type;
        this.size = size;
        this.selected = selected;
    }

    public BlockType.Type getType() {
        return type;
    }

    public Integer getSize() {
        return size;
    }

    public Boolean isSelected() {
        return selected;
    }
}
