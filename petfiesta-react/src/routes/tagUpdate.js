import {useLoaderData, useNavigate} from "react-router-dom";
import React, {useState} from "react";
import {updateTag} from "../api/tagService";

export function TagUpdate() {
    const navigate = useNavigate();

    const { tag } = useLoaderData();
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
                    onClick={updateTagClick}>
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