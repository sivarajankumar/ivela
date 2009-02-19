/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.web.action;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.commons.model.AttachPost;
import br.ufc.ivela.commons.model.File;
import br.ufc.ivela.commons.model.Forum;
import br.ufc.ivela.commons.model.Post;
import br.ufc.ivela.commons.model.Topic;
import br.ufc.ivela.ejb.interfaces.ForumRemote;
import br.ufc.ivela.ejb.interfaces.PostRemote;
import br.ufc.ivela.ejb.interfaces.TopicRemote;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.springframework.util.StringUtils;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * 
 */
public class PostAction extends GenericAction {

    private PostRemote postRemote;
    private Post post;
    private Topic topic;
    private List<Post> postList;
    private File file;
    private java.io.File[] upload;
    private String[] uploadContentType;
    private String[] uploadFileName;
    private InputStream downloadStream;
    private String contentLength;
    private String contentDisposition;
    private AttachPost attachPost;
    private final String path = Constants.FILE_UPLOAD_PATH;
    private Forum forum;
    private ForumRemote forumRemote;
    private TopicRemote topicRemote;

    public Forum getForum() {
        return forum;
    }

    public TopicRemote getTopicRemote() {
        return topicRemote;
    }

    public void setTopicRemote(TopicRemote topicRemote) {
        this.topicRemote = topicRemote;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public ForumRemote getForumRemote() {
        return forumRemote;
    }

    public void setForumRemote(ForumRemote forumRemote) {
        this.forumRemote = forumRemote;
    }
    
    

    /**
     * Add a quick answer
     * @return a list of quick answer
     */
    public String addQuickAnswer() {
        performValidationAdd();
        if (!hasActionErrors()) {
            topic = post.getTopic();
            topic = topicRemote.get(topic.getId());
            forum = forumRemote.get(topic.getForum().getId());
            post.setCreatedBy(getAuthenticatedUser());
            post.setCreatedAt(new Date());
            Long result = postRemote.add(post);
            if (result != null) {
                post = new Post();
                return list();
            }
        }
        return list();
    }

    /**
     * Add a new post 
     * @return
     */
    public String add() {
        performValidationAdd();
        if (!hasActionErrors()) {
            topic = post.getTopic();
            topic = topicRemote.get(topic.getId());
            forum = forumRemote.get(topic.getForum().getId());
            post.setCreatedBy(getAuthenticatedUser());
            post.setCreatedAt(new Date());

            Long idPost = postRemote.add(post);
            Post p = postRemote.get(idPost);

            if (upload != null) {
                for (int i = 0; i < upload.length; i++) {
                    java.io.File fileIo = upload[i];

                    if (fileIo != null) {
                        file = new File();

                        file.setTitle(uploadFileName[i]);
                        file.setDescription(uploadFileName[i]);
                        file.setAuthor((isUserLogged()) ? getAuthenticatedUser().getUsername() : "");
                        file.setKeywords(uploadFileName[i]);
                        file.setCourseId(p.getTopic().getForum().getGrade().getCourseId());
                        file.setFilename(uploadFileName[i]);
                        file.setMimetype(uploadContentType[i]);
                        file.setSentBy(p.getCreatedBy());
                        file.setUploadDate(new Date());
                        file.setPath(path + file.getFilename());

                        Long idFile = postRemote.addFile(fileIo, file);

                        if (idFile != null) {
                            File f = postRemote.getFile(idFile);

                            attachPost = new AttachPost();
                            attachPost.setFile(f);
                            attachPost.setPost(p);

                            postRemote.addAttach(attachPost);
                        }
                    }

                }
            }
            if (idPost != null) {
                post = new Post();
            }
            return list();
        }
        return list();
    }

    /**
     * Sets the variables to be used on the input form
     * @return
     */
    @Override
    public String input() {
        if (post.getId() != null) {
            Post p = postRemote.get(post.getId());
            post.setMessage("[quote]" + p.getMessage() + "[/quote]");
        }

        return INPUT;
    }

    /**
     * Retrieves the list of post by topicId
     * @return list view, if successful
     *         error view, otherwise
     */
    public String list() {
        // verifies if topicId is set
        forum = forumRemote.get(forum.getId());
        topic = topicRemote.get(topic.getId());
        
        if (topic.getId() != null) {
            boolean isAdministrator = ! (getAuthenticatedUser().getAuthentication().getId().equals(Constants.ROLE_USER));
            List<Post> list = postRemote.getPostList(getAuthenticatedUser().getId(), isAdministrator, true, topic.getId());

            for (Post post : list) {
                post.setAttachPosts(postRemote.getAttachsByPost(post.getId()));
                setTopic(post.getTopic());
                String message = post.getMessage();
                //message = message.replaceAll("\\[quote\\]", "<p class=\"quote-answer\">");
                //message = message.replaceAll("\\[/quote\\]", "</p>");
                post.setMessage(message);
            }

            setPostList(list);

            return "list";
        } else {
            // returns an error message
            return "list";
        }
    }

    /**
     * removes a post
     * @return list view, if successful
     *         error view, otherwise
     */
    public String remove() {
        // verifies if topicId is set
        post = postRemote.get(post.getId());
        topic = post.getTopic();
        topic = topicRemote.get(topic.getId());
        forum = forumRemote.get(topic.getForum().getId());
        performValidationRemove();
        if (!hasActionErrors()) {
            boolean result = postRemote.remove(post.getId());
            if (result) {
                return list();
            }
        }
        return list();
    }

    /**
     * Perform a validation in to the add method
     */
    private void performValidationAdd() {
        if (post == null) {
            addActionError(getText("post.input.validation.required"));
        }
        // verifies if the title is empty
        if (!StringUtils.hasText(post.getTitle())) {
            addActionError(getText("post.input.validation.title"));
        }
        // verifies if the message is empty
        if (!StringUtils.hasText(post.getMessage())) {
            addActionError(getText("post.input.validation.message"));
        }
    }

    private void performValidationRemove() {
        // verifies if there is an id
        if (post.getId() == null) {
            addActionError(getText("post.input.validation.requiredId"));
        } else {
            // verifies if this id is valid
            if (postRemote.get(post.getId()) == null) {
                addActionError(getText("post.input.validation.invalidId"));
            }
        }
    }

    public String download() {

        File dbfile = postRemote.getFile(file.getId());

        java.io.File f = new java.io.File(dbfile.getPath());
        try {
            downloadStream = new FileInputStream(f);
        } catch (FileNotFoundException ex) {
            logger.log(ex, "download error!");
            ex.printStackTrace();
        }

        setContentLength(new Long(f.length()).toString());
        setContentDisposition("filename=\"" + dbfile.getFilename() + "\"");

        return "download";
    }

    /**
     * Retrieves a file
     * @return file
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets a file
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Retrieves the value of post variable
     * @return post
     */
    public Post getPost() {
        return post;
    }

    /**
     * Sets the value of post variable
     * @return void
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * Retrieves the value of postRemoteost variable
     * @return PostRemote
     */
    public PostRemote getPostRemote() {
        return postRemote;
    }

    /**
     * Sets the value of postRemote variable
     * @return void
     */
    public void setPostRemote(PostRemote postRemote) {
        this.postRemote = postRemote;
    }

    /**
     * Retrieves the value of postList variable
     * @return List<Post>
     */
    public List<Post> getPostList() {
        return postList;
    }

    /**
     * Sets the value of postList variable
     * @return void
     */
    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    /**
     * Retrieves the post attached
     * @return attachPost
     */
    public AttachPost getAttachPost() {
        return attachPost;
    }

    /**
     * Sets the post attached
     * @param attachPost
     */
    public void setAttachPost(AttachPost attachPost) {
        this.attachPost = attachPost;
    }

    /**
     * Retrieve a topic
     * @return topic
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * Sets a topic
     * @param topic
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    /**
     * Retrieves a disposition content
     * @return contentDisposition
     */
    public String getContentDisposition() {
        return contentDisposition;
    }

    /**
     * Sets a disposition content
     * @param contentDisposition
     */
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    /**
     * Retrieves a content lenght
     * @return contentLength
     */
    public String getContentLength() {
        return contentLength;
    }

    /**
     * 
     * @param contentLength
     */
    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    /**
     * Retrieves a download stream
     * @return downloadStream
     */
    public InputStream getDownloadStream() {
        return downloadStream;
    }

    /**
     * Sets a download stream
     * @param downloadStream
     */
    public void setDownloadStream(InputStream downloadStream) {
        this.downloadStream = downloadStream;
    }

    /**
     * Retrieves a upload
     * @return upload
     */
    public java.io.File[] getUpload() {
        return upload;
    }

    /**
     * Sets a upload
     * @param upload
     */
    public void setUpload(java.io.File[] upload) {
        this.upload = upload;
    }

    /**
     * Retrieves a upload content type
     * @return uploadContentType
     */
    public String[] getUploadContentType() {
        return uploadContentType;
    }

    /**
     * Sets a upload content type
     * @param uploadContentType
     */
    public void setUploadContentType(String[] uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * Retrieves a uploaded file name
     * @return uploadFileName
     */
    public String[] getUploadFileName() {
        return uploadFileName;
    }

    /**
     * Sets a uploaded file name
     * @param uploadFileName
     */
    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}