import {useLoaderData, useNavigate} from "react-router-dom";
import React, {useState} from "react";
import {updateTag} from "../api/tagService";

export function TagUpdate() {
    const navigate = useNavigate();

    const { tag } = useLoaderData();
    const [name, setName] = useState('');

    return (
        <section className="form-container main-content">
            <div className="form update-form">
                <h1>Update tag</h1>
                <input
                    placeholder="Name"
                    className="form-control input-margin"
                    type="text"
                    onChange={(e) => setName(e.target.value)}
                />
                <button className="btn btn-primary" onClick={updateTagClick}>
                    Update
                </button>
            </div>
        </section>
    );

    async function updateTagClick() {
        tag.name = name
        await updateTag(tag.id, tag)
        navigate('/tags')
    }
}