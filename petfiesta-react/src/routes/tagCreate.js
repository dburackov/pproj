import {useNavigate} from "react-router-dom";
import React, {useState} from "react";
import {createTag} from "../api/tagService";

export function TagCreate() {
    const navigate = useNavigate();

    const [name, setName] = useState('');

    return (
        <section className="form-container main-content">
            <div className="form create-form">
                <h1>Create tag</h1>
                <input
                    placeholder="Name"
                    className="form-control input-margin"
                    type="text"
                    onChange={(e) => setName(e.target.value)}
                />
                <button className="btn btn-success" onClick={addTagClick}>Create</button>
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