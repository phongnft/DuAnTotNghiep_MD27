package com.example.duantotnghiep_md27.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentData {
    @SerializedName("data")
    List<Comment> commentList;

    public CommentData(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
