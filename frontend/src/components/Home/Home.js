import { useState, useEffect } from "react";
import React from "react";
import Post from "../Post/Post";
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import PostForm from "../Post/PostForm";

function Home() {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [postList, setPostList] = useState([]);

    const refreshPosts = () => {
        fetch("/posts")
        .then(res => res.json())
        .then(
            (result) => {
                setIsLoaded(true);
                setPostList(result);
            },
            (error) => {
                setIsLoaded(true);
                setError(error);
            }
        )
    }

    useEffect(() => {
       refreshPosts();
    }, [postList])

    if (error) {
        return <div>Error !!</div>;
    } else if (!isLoaded) {
        return <div>Loading..</div>
    } else {
        return (
            <React.Fragment>
                <CssBaseline />
                <div>
                <Box sx={{ bgcolor: '#cfe8fc', height: '100vh', justifyContent: "center", alignItems: "center", display: "flex", flexWrap: "wrap"}}>
                <PostForm key={1} userId = {1} userName = {"ddd"} refreshPosts = {refreshPosts}/>
                        {postList.map(post => (
                            <Post postId={post.postId} userId = {post.userId} userName = {post.userName} title={post.title} content={post.content} likes={post.postLikes}/>
                        ))}
                    </Box>    
                </div>
            </React.Fragment>
        )
    }
}

export default Home;