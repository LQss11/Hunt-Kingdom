<?php


namespace EcommerceBundle\Entity;


class Link
{
    private $link ="";

    /**
     * @return string
     */
    public function getLink()
    {
        return $this->link;
    }

    /**
     * @param string $link
     */
    public function setLink($link)
    {
        $this->link = $link;
    }




    public function __toString(){
        return $this->link;
    }


}