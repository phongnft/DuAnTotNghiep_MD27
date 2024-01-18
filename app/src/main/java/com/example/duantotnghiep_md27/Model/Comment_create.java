package com.example.duantotnghiep_md27.Model;

public class Comment_create {
    private String productId;
    private String userId;
    private String commentText;

    public Comment_create(String productId, String userId, String commentText) {
        this.productId = productId;
        this.userId = userId;
        this.commentText = commentText;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
