import {useNavigate} from "react-router-dom";
import React, {useState} from "react";
import {createTag} from "../api/tagService";

export function TagCreate() {
    const navigate = useNavigate();

    const [name, setName] = useState('');

    return (
        <section className="form-container">
            <div className="form">
                <input
                    placeholder="Name"
                    className="form-control"
                    type="text"
                    onChange={(e) => setName(e.target.value)}
                />
                <button
                    onClick={addTagClick}>
                    Add
                </button>
            </div>
        </section>
    );

    async function addTagClick() {
        let tag = {}
        tag.name = name
        await createTag(tag)
        navigate('/tags')
    }
}