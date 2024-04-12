import React, { useState, useEffect,useContext } from 'react';
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
    const [reload, setReload] = useState(false);

    useEffect(() => {
        loadTags();
    }, [reload]);

    return (
        <section className="main-content">
            <div className="table-title tags-table">
                <h2>Tags</h2>
                <Link to="create/" className="btn btn-success">Create</Link>
                <table className="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        {Tags.map(tag =>
                            <tr key={tag.id}>
                                <td>{tag.name}</td>
                                <td>
                                    <Link to={`update/${tag.id}`} className="btn btn-outline-primary">Update</Link>
                                </td>
                                <td>
                                    <button 
                                        onClick={() => removeTag(tag.id)} 
                                        value={tag.id}
                                        className="btn btn-outline-danger">
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