import React, { useState } from 'react';
import MDEditor from "@uiw/react-md-editor";

function Comments({ comments, onAddComment }) {
    const [newComment, setNewComment] = useState('');
    const [author, setAuthor] = useState('');

    const handleAddComment = () => {
        if (author.trim() === '' || newComment.trim() === '') return;
        onAddComment(author.trim(), newComment.trim());
        setNewComment('');
        setAuthor('');
    };

    return (
        <div className="comment-section mt-4">
            <h5 className="text-primary mb-3">Comments</h5>
            <div className="comments-list mb-3" style={{
                maxHeight: '300px',
                overflowY: 'auto',
                border: '1px solid #ddd',
                borderRadius: '5px',
                padding: '10px',
                backgroundColor: '#f9f9f9'
            }}>
                {comments.length > 0 ? (
                    comments.map((comment, index) => (
                        <div
                            key={index}
                            className="comment-item mb-3"
                            style={{
                                borderBottom: '1px solid #eee',
                                paddingBottom: '10px',
                                marginBottom: '10px',
                            }}
                        >
                            <strong style={{color: '#007bff'}}>{comment.author || 'Anonymous'}:</strong>
                            <p className="mb-1" style={{margin: '5px 0', color: '#555'}}>{comment.content}</p>
                            <small className="text-muted">
                                {new Date(comment.publishDate).toLocaleString()}
                            </small>
                        </div>
                    ))
                ) : (
                    <p className="text-muted">No comments yet. Be the first to comment!</p>
                )}
            </div>
            <div className="input-group">
                <input
                    type="text"
                    className="form-control"
                    placeholder="Author"
                    value={author}
                    onChange={(e) => setAuthor(e.target.value)}
                />
                <MDEditor
                    data-color-mode="light"
                    value={newComment}
                    onChange={setNewComment}
                />
                <button className="btn btn-primary" onClick={handleAddComment}>
                    Add
                </button>
            </div>
        </div>
    );
}

export default Comments;