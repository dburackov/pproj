import React, { useState, useEffect } from 'react';
import { AppContext } from "../contexts/contexts";
import { getTag, getTags, createTag, deleteTag, updateTag} from "../api/tagService"
import { Link, useLoaderData, useNavigate } from "react-router-dom";

export async function loader({ params }) {
    const tag = await getTag(params.tagId);
    return { tag };
}

export default function Tags() {
    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    const [Tags, setTags] = useState([]);
    const [name, setName] = useState('');

    useEffect(() => {
        loadChats();
    }, []);

    return (
        <section >
            <div className='table-title'>
                <h2>Tags</h2>
                
                <table className="table table-striped table-hover">   
                    <thead>
                        <tr>
                            <th>
                                Name
                            </th>
                            <th>
                                <Link to="add/" className="btn btn-success">Add</Link>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {Tags.map(tag =>
                            <tr key={tag.id}>
                                <td>
                                    {tag.name}
                                </td>
                                <td>
                                    <Link to={`update/${tag.id}`} className="btn btn-info">Update</Link>
                                    |
                                    <button 
                                        onClick={() => removeTag(tag.id)} 
                                        value={tag.id}
                                        className="btn btn-danger">
                                            Remove
                                    </button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </section>
    );

    async function loadTags() {
        const Tags = await getTags()
        setTags(Tags)
    }

    async function removeTag(id) {
        await deleteTag(id)
        setReload(!reload)
    }
}

export function TagsAdd() {
    const navigate = useNavigate();

    const [name, setName] = useState('');

    return (
        <section class="form-container">
            <div class="form">
                <input
                    placeholder="Name"
                    class="form-control"
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
        tag.id = crypto.randomUUID();
        tag.name = name
        await addTag(tag)
        navigate('/Tags')
    }
}

export function TagsUpdate() {
    const navigate = useNavigate();

    const { tag } = useLoaderData();
    const [name, setName] = useState('');

    return (
        <section class="form-container">
            <div class="form">
                <input
                    placeholder="Name"
                    class="form-control"
                    type="text"
                    onChange={(e) => setName(e.target.value)}
                />
                <button
                    onClick={updateTagClick}>
                    Изменить
                </button>
            </div>
        </section>
    );

    async function updateTagClick() {
        tag.name = name
        await updateTag(tag)
        navigate('/Tags')
    }
}