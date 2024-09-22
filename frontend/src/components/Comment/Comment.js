import { CardContent, OutlinedInput } from "@mui/material";
import React from "react";
import {InputAdornment} from "@mui/material";
import { Link } from "react-router-dom";
import Avatar from '@mui/material/Avatar';

function Comment(props){
    const {content, userId, userName} = props;
    
    return(
        <CardContent>
            <OutlinedInput 
            disabled
            id="outlined-adornment-amount"
            multiline
            inputProps={{ maxLength: 25 }}
            fullWidth
            value={content}
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
            ></OutlinedInput>
        </CardContent>

    )
}


export default Comment;