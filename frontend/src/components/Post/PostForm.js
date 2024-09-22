import React from "react";
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { Link } from "react-router-dom";
import OutlinedInput from '@mui/material/OutlinedInput';
import { Button, InputAdornment } from "@mui/material";
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';


const ExpandMore = styled((props) => {
    const { expand, ...other } = props;
    return <IconButton {...other} />;
})(({ theme }) => ({
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
    }),
    variants: [
        {
            props: ({ expand }) => !expand,
            style: {
                transform: 'rotate(0deg)',
            },
        },
        {
            props: ({ expand }) => !!expand,
            style: {
                transform: 'rotate(0deg)',
            },
        },
    ],
}));

function PostForm(props) {
    const { userId, userName, refreshPosts} = props;
    const [title, setTitle] = React.useState("");
    const [content, setContent] = React.useState("");
    const [isSent, setIsSent] = React.useState(false);

    const savePost = () => {
        fetch('/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                title : title,
                userId : userId,
                content : content
            }),
        })
        .then((res) => res.json())
        .catch((err) => console.log("error ", err));

    }

    const handleSubmit = () => {
        savePost();
        setIsSent(true);
        setTitle("");
        setContent("");
        refreshPosts();
    }

    const handleTitle = (value) => {
        setTitle(value);
        setIsSent(false);
    }

    const handleContent = (value) => {
        setContent(value)
        setIsSent(false);
    }

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
          return;
        }
    
        setIsSent(false);
    }
    

    return (
        <div>
            <Snackbar open={isSent} autoHideDuration={1100} onClose={handleClose}  anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}>
            <Alert
                onClose={handleClose}
                severity="success"
                variant="filled"
                sx={{ width: '100%' }}
            >
                Post is a success!
            </Alert>
            </Snackbar>
            <Card sx={{ width: 800, textAlign: "left", margin: 20 }}>
                <CardHeader
                    avatar={
                        <Link to={{ pathname: '/users/' + userId }} style={{
                            color: 'white',
                            textDecoration: 'none',
                            marginLeft: '16px'  // Boşluk eklemek için isteğe bağlı
                        }}>
                            <Avatar sx={{ background: "linear-gradient(to right top, #0cdae3, #70e0e3, #9fe6e5, #c6ebe9, #e8f0ef)" }} aria-label="recipe">
                                {userName.charAt(0).toUpperCase()}
                            </Avatar>
                        </Link>
                    }
                    title={<OutlinedInput
                        id="outlined-adornment-amount"
                        multiline
                        placeholder="Title"
                        inputProps={{ maxLength: 25 }}
                        fullWidth
                        value={title}
                        onChange={(input) => handleTitle(input.target.value)}
                    >
                    </OutlinedInput>}
                />
                <CardContent>
                    <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                        <OutlinedInput
                            id="outlined-adornment-amount"
                            multiline
                            placeholder="Content"
                            inputProps={{ maxLength: 250 }}
                            fullWidth
                            value={content}
                            onChange={(input) => handleContent(input.target.value)}
                            endAdornment={
                                <InputAdornment position="end">
                                    <Button variant="contained" 
                                    color="white" 
                                    sx={{ background: "linear-gradient(to right top, #0cdae3, #70e0e3, #9fe6e5, #c6ebe9, #e8f0ef)"}} 
                                    onClick={handleSubmit}
                                    >Post</Button>
                                </InputAdornment>
                            }
                        >
                        </OutlinedInput>
                    </Typography>
                </CardContent>
            </Card>
        </div>
    )
}


export default PostForm;