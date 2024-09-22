import React, { useRef, useState, useEffect } from "react";
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import FavoriteIcon from '@mui/icons-material/Favorite';
import CommentIcon from '@mui/icons-material/Comment';
import { Link } from "react-router-dom";
import { Container } from "@mui/material";
import Comment from "../Comment/Comment";
import CommentForm from "../Comment/CommentForm";

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

function Post(props) {
  const { title, content, userId, userName, postId } = props;
  const [expanded, setExpanded] = useState(false);
  const [liked, setLiked] = useState(false);
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [commentList, setCommentList] = useState([]);
  const isInitialMount = useRef(true);

  const handleExpandClick = () => {
    setExpanded(!expanded);
    refreshComments();
    console.log(commentList);
  };

  const handleLike = () => {
    setLiked(!liked);
  }

  const refreshComments = () => {
    fetch("/comments?postId="+postId)
    .then(res => res.json())
    .then(
        (result) => {
            setIsLoaded(true);
            setCommentList(result);
        },
        (error) => {
            setIsLoaded(true);
            setError(error);
        }
  )}
  useEffect(() => {
    if(isInitialMount.current)
      isInitialMount.current = false;
    else
      refreshComments();
  }, [commentList])

  return (
    <div className="postContainer">
      <Card sx={{ width: 800, textAlign: "left", margin: 20}}>
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
          title={title}
        />
        <CardContent>
          <Typography variant="body2" sx={{ color: 'text.secondary' }}>
            {content}
          </Typography>
        </CardContent>
        <CardActions disableSpacing>
          <IconButton 
          onClick={handleLike}
          aria-label="add to favorites">
            <FavoriteIcon sx={liked ? {color: "red" } : null}/>
          </IconButton>
          <ExpandMore
            expand={expanded}
            onClick={handleExpandClick}
            aria-expanded={expanded}
            aria-label="show more"
          >
            <CommentIcon />
          </ExpandMore>
        </CardActions>
        <Collapse in={expanded} timeout="auto" unmountOnExit>
          <Container fixed>
            {error? "error" : 
            isLoaded ? commentList.map(comment => (
              <Comment userId={1} userName={"USER"} content={comment.content}></Comment>
            )): "Loading"}
            <CommentForm userId={1} userName={"USER"} postId={postId}></CommentForm>
          </Container>
        </Collapse>
      </Card>
    </div>
  )
}


export default Post;