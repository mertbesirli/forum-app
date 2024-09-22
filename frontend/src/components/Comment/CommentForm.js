import { CardContent, OutlinedInput } from "@mui/material";
import React from "react";
import {InputAdornment, Button} from "@mui/material";
import { Link } from "react-router-dom";
import Avatar from '@mui/material/Avatar';

function CommentForm(props){
    const {userId, userName, postId} = props;
    const [content, setContent] = React.useState("");


    const saveComment = () => {
        fetch('/comments', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                postId : postId,
                userId : userId,
                content : content
            }),
        })
        .then((res) => res.json())
        .catch((err) => console.log("error ", err));

    }

    const handleSubmit = () => {
        saveComment();
        setContent("");
    }

    const handleContent = (value) => {
        setContent(value)
    }


    return(
        <CardContent>
            <OutlinedInput 
            id="outlined-adornment-amount"
            multiline
            inputProps={{ maxLength: 250 }}
            fullWidth
            startAdornment={
                <InputAdornment position="start">
                   <Link to={{ pathname: '/users/' + userId }} style={{
                            color: 'white',
                            textDecoration: 'none',
                            marginLeft: '16px'  // Boşluk eklemek için isteğe bağlı
                        }}>
                            <Avatar sx={{ background: "linear-gradient(to right top, #0cdae3, #70e0e3, #9fe6e5, #c6ebe9, #e8f0ef)" }} aria-label="recipe">
                                {userName.charAt(0).toUpperCase()}
                            </Avatar>
                        </Link>
                </InputAdornment>
            }
            endAdornment={
                <InputAdornment position="end">
                    <Button variant="contained" 
                        color="white" 
                        sx={{ background: "linear-gradient(to right top, #0cdae3, #70e0e3, #9fe6e5, #c6ebe9, #e8f0ef)"}} 
                        onClick={handleSubmit}
                        >Comment</Button>
                </InputAdornment>
            }
            value={content}
            onChange={(input) => handleContent(input.target.value)}
            ></OutlinedInput>
        </CardContent>

    )
}


export default CommentForm;