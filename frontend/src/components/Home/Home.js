import { useState, useEffect } from "react";
import React from "react";
import Post from "../Post/Post";
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';

function Home() {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [postList, setPostList] = useState([]);

    useEffect(() => {
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
    }, [])

    if (error) {
        return <div>Error !!</div>;
    } else if (!isLoaded) {
        return <div>Loading..</div>
    } else {
        return (
            <React.Fragment>
                <CssBaseline />
                <Container fixed>
                <Box sx={{ bgcolor: '#cfe8fc', height: '100vh', justifyContent: "center", alignItems: "center", display: "flex", flexWrap: "wrap"}}>
                        {postList.map(post => (
                            <Post key={post.id} userId = {post.userId} userName = {post.userName} title={post.title} content={post.content} />
                        ))}
                    </Box>    
                </Container>
            </React.Fragment>
        )
    }
}

export default Home;