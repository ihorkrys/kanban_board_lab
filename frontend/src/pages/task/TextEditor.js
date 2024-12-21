import React from 'react';
import { useEditor, EditorContent } from '@tiptap/react';
import StarterKit from '@tiptap/starter-kit';

function TextEditor({ value, onChange }) {
    const editor = useEditor({
        extensions: [StarterKit],
        content: value,
        onUpdate: ({ editor }) => {
            const html = editor.getHTML();
            onChange(html);
        },
    });

    return (
        <div>
            <div style={{ marginBottom: '10px' }}>
                <button
                    type="button"
                    onClick={() => editor.chain().focus().toggleBold().run()}
                    style={{
                        fontWeight: editor?.isActive('bold') ? 'bold' : 'normal',
                        marginRight: '10px',
                    }}
                >
                    Bold
                </button>
                <button
                    type="button"
                    onClick={() => editor.chain().focus().toggleItalic().run()}
                    style={{
                        fontStyle: editor?.isActive('italic') ? 'italic' : 'normal',
                        marginRight: '10px',
                    }}
                >
                    Italic
                </button>
                <button
                    type="button"
                    onClick={() => editor.chain().focus().toggleUnderline().run()}
                    style={{
                        textDecoration: editor?.isActive('underline') ? 'underline' : 'none',
                        marginRight: '10px',
                    }}
                >
                    Underline
                </button>
            </div>
            <div
                style={{
                    border: '1px solid #ddd',
                    padding: '10px',
                    minHeight: '200px',
                    backgroundColor: '#fff',
                }}
            >
                <EditorContent editor={editor} />
            </div>
        </div>
    );
}

export default TextEditor;