import React from "react";
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { red } from '@mui/material/colors';
import FavoriteIcon from '@mui/icons-material/Favorite';
import CommentIcon from '@mui/icons-material/Comment';
import { Link } from "react-router-dom";

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
  const { title, content, userId, userName } = props;
  const [expanded, setExpanded] = React.useState(false);
  const [liked, setLiked] = React.useState(false);

  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  const handleLike = () => {
    setLiked(!liked);
  }

  return (
    <div className="postContainer">
      <Card sx={{ width: 800, textAlign: "left"}}>
        <CardHeader
          avatar={
            <Link to={{ pathname: '/users/' + userId }} style={{
              color: 'white', 
              textDecoration: 'none',
              marginLeft: '16px'  // Boşluk eklemek için isteğe bağlı
          }}>
              <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
              {userName.charAt(0).toUpperCase()}
            </Avatar>
            </Link>
          }
          title={title}
          subheader="September 14, 2016"
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
          <CardContent>
            Comment
          </CardContent>
        </Collapse>
      </Card>
    </div>
  )
}


export default Post;